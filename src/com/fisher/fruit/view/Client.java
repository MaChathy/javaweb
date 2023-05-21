package com.fisher.fruit.view;

import com.fisher.fruit.controller.Menu;

/**
 * This program demonstrates se_projects
 *
 * @author fisher
 * @version 1.0.1 2023/5/20 - 21:12
 */
public class Client {
    public static void main(String[] args) {
        Menu m = new Menu() ;

        boolean flag = true ;
        while(flag){
            //调用显示主菜单的方法
            int slt = m.showMainMenu();
            switch(slt){
                case 1:
                    //显示库存列表
                    m.showFruitList();
                    break;
                case 2:
                    m.addFruit();
                    break;
                case 3:
                    m.showFruitInfo();
                    break;
                case 4:
                    m.delFruit();
                    break;
                case 5:
                    flag=m.exit();
                    break;
                default:
                    System.out.println("你不按套路出牌！");
                    break;
            }
        }
        System.out.println("谢谢使用！再见！");
    }
}
