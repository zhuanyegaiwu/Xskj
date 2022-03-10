package com.example.xskj.taurus

import android.Manifest
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.*
import android.telephony.cdma.CdmaCellLocation
import android.telephony.gsm.GsmCellLocation
import android.util.Log
import com.example.xskj.R
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.fragment_diagnostics.*


class DiagnosticsFt : CommFT() {
    var wifiName = ""
    override fun getTitleType(): Int {
        return 1
    }

    override fun getTitleText(): String = "Diagnostics"

    override fun getViewId(): Int = R.layout.fragment_diagnostics

    override fun initData() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
            .requestEach(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION

            )
            .subscribe { permission ->

                if (permission.granted) {
                    Log.e("zpl", "granted")
                } else if (permission.shouldShowRequestPermissionRationale) {
                    Log.e("zpl", "shouldShowRequestPermissionRationale")
                } else {
                    Log.e("zpl", "else")
                    getMobileDbm()
                }
            }

    }

    override fun initView() {
        tvNetworkName.text = getNetWorkState(requireContext())
        tvNetworkStrength.text = when (tvNetworkName.text) {
            "4G", "3G", "2G" -> get4GOr5GSignalStrength(requireContext())
            "no network" -> "unknown"
            else -> getWifiSignalStrength(requireContext())
        }
        when (isConnected(requireContext())) {
            true -> tvConnect.text = "Connected"
            else -> tvConnect.text = "Disconnect"
        }

    }


    override fun initLisenter() {

    }

    /**
     * -1 未联网
     * 0 网络类型为运营商（移动/联通/电信）
     * 1 网络类型为WIFI（无线网）
     */
    private fun getNetWorkState(context: Context): String {
        var type = ""
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return "no network"
        val type1 = activeNetworkInfo.type
        type = when (type1) {
            ConnectivityManager.TYPE_MOBILE -> getNumG()
            ConnectivityManager.TYPE_WIFI -> getWifiName(context)
            else -> "no network"
        }
        return type
    }

    private fun getNumG(): String {
        val telephonyManager =
            requireContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_LTE -> return "4G"
            TelephonyManager.NETWORK_TYPE_HSDPA,
            TelephonyManager.NETWORK_TYPE_HSPA,
            TelephonyManager.NETWORK_TYPE_HSUPA,
            TelephonyManager.NETWORK_TYPE_UMTS -> return "3G"
            else -> "2G"
        }
    }

    /**
     * 获取Wifi 名称
     */
    private fun getWifiName(context: Context): String {
        val wifiManager = context.getSystemService(WIFI_SERVICE) as WifiManager?
        val wifiInfo = wifiManager!!.connectionInfo
        return wifiInfo.ssid.substring(1, wifiInfo.ssid.length - 1)
    }

    /**
     * 网络是否连接
     */
    private fun isConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
                if (networkCapabilities != null) {
                    return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                }
            } else {
                val networkInfo = manager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            }
        }
        return false
    }

    /**
     * 获取4G/3G/2G信号强度
     */
    lateinit var MyPhoneListener: PhoneStateListener
    open fun get4GOr5GSignalStrength(context: Context): String {
        var bin: String = ""
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        MyPhoneListener = object : PhoneStateListener() {
            override fun onSignalStrengthsChanged(signalStrength: SignalStrength) {
                val signalinfo = signalStrength.toString()
                val parts = signalinfo.split(" ").toTypedArray()
                val ltedbm: String = parts[9]
                //这个dbm 是2G和3G信号的值
                val asu = signalStrength.gsmSignalStrength
                val dbm = -113 + 2 * asu
                if (telephonyManager.networkType == TelephonyManager.NETWORK_TYPE_LTE) {
                    bin = "Excellent"
                } else if (telephonyManager.networkType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || telephonyManager.networkType == TelephonyManager.NETWORK_TYPE_HSPA
                    || telephonyManager.networkType == TelephonyManager.NETWORK_TYPE_HSUPA
                    || telephonyManager.networkType == TelephonyManager.NETWORK_TYPE_UMTS
                ) {
                    bin = if (dbm > -75) ("Excellent") else if (dbm > -85) "Common" else "Poor"
                } else {
                    bin =
                        if (asu < 0 || asu >= 99) "No Network" else if (asu >= 16) "Excellent" else if (asu >= 6) "Common" else "Poor"
                }
                tvNetworkStrength?.text = bin
                super.onSignalStrengthsChanged(signalStrength)
            }
        }
        telephonyManager.listen(MyPhoneListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS)
        return bin
    }


    /**
     * 获wifi取信号强度
     */
    private fun getWifiSignalStrength(context: Context): String {
        var wifiManager = context.getSystemService(WIFI_SERVICE) as WifiManager;
        var info = wifiManager.getConnectionInfo();
        if (info.getBSSID() != null) {
            // 链接信号强度
            var strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
            // 链接速度
            var speed = info.getLinkSpeed();
            // 链接速度单位
            var units = WifiInfo.LINK_SPEED_UNITS;
            // Wifi源名称
            var ssid = info.getSSID();
            val rssi = info.getRssi()
            /**
             * 0到-50表示信号最好，-50到-70表示信号偏差，小于-70表示最差
             */
            if (rssi >= -50 && rssi <= 0) {
                return "Excellent"
            } else if (rssi > -70 && rssi < -50) {
                return "Common"
            } else {
                return "Poor"
            }
        }
        return ""
    }

    fun getMobileDbm() {
        var dbm = -1
        val tm = requireContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cellInfoList = tm.allCellInfo
        Log.e("zpl", cellInfoList.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (null != cellInfoList) {
                for (cellInfo in cellInfoList) {

                    if (cellInfo is CellSignalStrength) {
                        Log.e("zpl", "1111111111111")
                    }
                }
            }
        }
    }
}