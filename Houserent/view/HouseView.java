package com.hspedu.Houserent.view;

import com.hspedu.Houserent.domain.House;
import com.hspedu.Houserent.service.HouseService;
import com.hspedu.Houserent.utility.Utility;
import com.hspedu.encap.Homework.Homework10.Doctor;

/*
 * 1，显示主菜单
 * 2.接受用户的输入
 * 3.调用HouseService完成对房屋信息的操作
 */
public class HouseView {
    private boolean loop = true;//控制菜单
    private char key = ' ';//接收用户输入
    HouseService houseService = new HouseService(10);

    //新增房源
    public void addHouses() {
        System.out.println("=====================添加房源=====================");
        System.out.print("姓名:");
        String name = Utility.readString(8);
        System.out.print("电话:");
        String phone = Utility.readString(12);
        System.out.print("地址:");
        String address = Utility.readString(20);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态(未出租/已出租)：");
        String state = Utility.readString(3);
        System.out.println("=====================添加完成=====================");
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.addHouses(newHouse)) {
            System.out.println("添加成功！！！");
        } else {
            System.out.println("添加失败~");
        }
    }

    //查找房屋
    public void findHouses() {
        System.out.println("=====================查找房屋=====================");
        System.out.println("请输入你要查找id:");
        int findId = Utility.readInt();
        House house = houseService.findHouse(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("要查找的房屋不存在~");
        }
        System.out.println("====================查找房屋完成===================");
    }

    //删除房屋
    public void delHouses() {
        System.out.println("=====================删除房屋=====================");
        System.out.println("请选择待删除房屋编号(-1退出):");
        int delID = Utility.readInt();
        if (delID == -1) {
            System.out.println("您放弃了删除操作！");
            return;
        }
        System.out.println("确认是否删除(Y/N):请小心选择:");
        char a = Utility.readConfirmSelection();
        if (a == 'Y') {
            //调用删除
            if (houseService.delHouse(delID)) {
                System.out.println("您已成功删除信息！");
            } else {
                System.out.println("删除失败！没有找到您要删除的信息~");
            }
        } else {
            System.out.println("删除操作被取消！");
            //返回
        }
        System.out.println("====================删除房屋完成===================");
    }

    //修改房屋
    public void updateHouses() {
        System.out.println("=====================修改房屋=====================");
        System.out.println("请选择待修改房屋编号(-1退出):");
        int updateID = Utility.readInt();
        if (updateID == -1) {
            System.out.println("您放弃了更新操作！");
            return;
        }
        House house = houseService.findHouse(updateID);
        if (house == null) {
            System.out.println("要查找的房屋不存在~");
            return;
        }
        System.out.println("姓名(" + house.getName() + "):");
        String name = Utility.readString(8, "");
        if (!"".equals(name)) {
            house.setName(name);
        }

        System.out.println("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }

        System.out.println("地址(" + house.getAddress() + "):");
        String address = Utility.readString(20, "");
        if (!"".equals(address)) {
            house.setAddress(address);
        }

        System.out.println("月租(" + house.getRent() + "):");
        int rent = Utility.readInt();
        if (rent != 0) {
            house.setRent(rent);
        }

        System.out.println("状态(" + house.getState() + "):");
        String state = Utility.readString(3, "");
        if(!"".equals(state)){
            house.setState(state);
        }

        System.out.println("====================修改房屋完成===================");
    }


    //房屋列表
    public void listHouses() {
        System.out.println("=====================房屋列表=====================");
        System.out.println("编号\t\t" + "房主\t\t" + "电话\t\t" + "地址\t\t" + "月租\t\t" + "状态(未出租/已出租)");
        House house[] = houseService.getHouses();
        for (int i = 0; i < house.length; i++) {
            if (house[i] != null) {
                System.out.println(house[i]);
            } else {
                break;
            }
        }
        System.out.println("====================房屋列表完成====================");
    }

    //退出
    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            System.out.println("您已成功退出系统！");
            loop = false;
        } else {
            System.out.println("系统退出被取消！");
        }
    }

    //显示主菜单
    public void mainMenu() {
        do {
            System.out.println("================房屋出租系统菜单================");
            System.out.println("\t\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t\t2 查 找 房 屋 信 息");
            System.out.println("\t\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t\t6   退   出");
            System.out.print("请输入你的选择(1-6)：");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouses();
                    break;
                case '2':
                    findHouses();
                    break;
                case '3':
                    delHouses();
                    break;
                case '4':
                    updateHouses();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
                default:
                    System.out.println("输入有误~ 请重新输入！");
            }
        } while (loop);
    }
}
