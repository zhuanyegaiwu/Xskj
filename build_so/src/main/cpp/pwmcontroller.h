#ifndef PWMCONTROLLER_H
#define PWMCONTROLLER_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <iostream>
#include <memory>
#include <array>
class PwmController :std::enable_shared_from_this<PwmController>
{

public:
    using Ptr = std::shared_ptr<PwmController>;
    static Ptr getPtr();
    std::string execCommand(const char* cmd) {
        std::array<char, 128> buffer;
        std::string result;
        std::unique_ptr<FILE, decltype(&pclose)> pipe(popen(cmd, "r"), pclose);
        if (!pipe) {
            throw std::runtime_error("popen() failed!");
        }
        while (fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
            result += buffer.data();
        }
        return result;
    }

    int  PUL1_On(void)  //A桶电机1开启
    {
       return  system("echo 1 > /sys/class/gpio/gpio46/value");//1
    }
    void PUL1_OFF(void) //A桶电机1关闭
    {
        system("echo 0 > /sys/class/gpio/gpio46/value");//1
    }


    void PUL2_On(void)  //A桶电机2开启
    {
        system("echo 1 > /sys/class/gpio/gpio49/value");//1
    }
    void PUL2_OFF(void) //A桶电机2关闭
    {
        system("echo 0 > /sys/class/gpio/gpio49/value");//1
    }



    void PUL3_On(void)  //B桶电机1开启
    {
        system("echo 1 > /sys/class/gpio/gpio50/value");//
    }
    void PUL3_OFF(void) //B桶电机1关闭
    {
        system("echo 0 > /sys/class/gpio/gpio50/value");//
    }



    void PUL4_On(void)  //B桶电机2关闭
    {
        system("echo 1 > /sys/class/gpio/gpio51/value");//
    }
    void PUL4_OFF(void) //B桶电机2关闭
    {
        system("echo 0 > /sys/class/gpio/gpio51/value");
    }



    void PWM1_On(int hz)  //下风扇风扇开启
    {
        std::string cmd = "echo " + std::to_string(hz) + "  > /sys/class/pwm/pwmchip1/pwm0/duty_cycle";
        std::cout<<cmd<<std::endl;
        system(cmd.c_str());// 1
    }
    void PWM1_OFF(void) //下风扇风扇关闭
    {
        system("echo 1500000 > /sys/class/pwm/pwmchip1/pwm0/duty_cycle");// 1
    }

    void PWM2_On(int hz)  //上风扇风扇开启
    {
        system("echo 1 > /sys/class/pwm/pwmchip3/pwm0/enable");
        std::string cmd = "echo " + std::to_string(hz) +" > /sys/class/pwm/pwmchip3/pwm0/duty_cycle";
        std::cout<<cmd<<std::endl;
        system(cmd.c_str());// 1
    }
    void PWM2_OFF(void) //上风扇风扇关闭
    {
        system("echo 0 > /sys/class/pwm/pwmchip3/pwm0/enable");// 1
    }
    int getLiquidSensor(int id){        //获取液体传感器数值   0 - 3 id
//        printf("getLiquidSensor id:%d\n",id);
        switch (id) {
        case 0:
            return std::atoi( execCommand("cat /sys/class/gpio/gpio44/value").c_str() );
        case 1:
            return std::atoi( execCommand("cat /sys/class/gpio/gpio48/value").c_str() );
        case 2:
            return std::atoi( execCommand("cat /sys/class/gpio/gpio52/value").c_str() );
        default:
            return -999;
        }
    }
    int getDoorSendor(int id){          //获取门阀传感器数值  0 - 1
//        printf("getDoorSendor id:%d\n",id);
        switch (id) {
        case 0:
            return std::atoi( execCommand("cat /sys/class/gpio/gpio47/value").c_str() );
        case 1:
            return std::atoi( execCommand("cat /sys/class/gpio/gpio45/value").c_str() );
        default:
            return -999;
        }
    }

private:
    PwmController();


};

#endif // PWMCONTROLLER_H
