package com.hspedu.Houserent.service;

import com.hspedu.Houserent.domain.House;

import javax.swing.*;

//底层逻辑
public class HouseService {

    private House[] houses;//保存House对象
    private int houseNums = 1;//记录当前多少个房屋信息
    private int idConter = 1;//记录当前的信息编号

    //构造器
    public HouseService(int ArrSize) {

        houses = new House[ArrSize];

        //测试 初始化数组
        houses[0] = new House(1, "张三", "10086", "城关区", 2000, "已出租");

    }

    public House[] getHouses() {
        return houses;
    }

    //新增房源
    public Boolean addHouses(House newHouse) {
        if (houseNums == houses.length) {
            System.out.println("数组已满 不能再添加了~");
            return false;
        }
        houses[houseNums++] = newHouse;
        //houses[houseNums] = newHouse;
        //houseNums++;

        newHouse.setId(++idConter);
        //idConter++;
        //newHouse.setId(idConter);
        return true;
    }

    //查找房屋
    public House findHouse(int findId) {
        //遍历数组
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }

    //删除房屋 传入delID 返回boolean 和 //修改房屋的查找部分
    public boolean delHouse(int delID) {
        /*
         *遍历数组 寻找要删除的目标
         *通过遍历来匹配 要删除的ID 和 房屋列表的ID
         *目的是为了找到要删除的ID的信息所在数组内对应的位置
         */
        int index = -1;
        for (int i = 0; i < houseNums; i++) {

            if (delID == houses[i].getId()) {
                index = i;
            }
        }
        //说明没找到 返回false
        if (index == -1) {
            return false;
        }

        //
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNums] = null;
        //houses[houseNums] = null;
        //houseNums--;
        return true;
    }
}
