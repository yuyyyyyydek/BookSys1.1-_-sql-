package com.book.one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.*;

public class Updata {//此窗口需要从Upd窗口打开才可运行

    //图书数量不为负数的信息框内容声明
    private JFrame jframeamount;//图书数量不为负数提示框,

    private JLabel jLabel;//数量内容不为负数，体术信息框内容

    private JButton bdef;//确认信息按钮，图书数量不为负数窗体的确认按钮



    Font font2 = new Font("Serif", Font.BOLD, 17);//设置文字大小
    private JButton bupdata2;//updata界面 修改图书信息按钮声明，完成数据修改的按钮
    private JLabel jLabel3;//updata面板 文本域，信息为需要修改的图书名字

    private JLabel jLabel4;//updata面板  文本域，信息为需要修改的图书作者

    private JLabel jLabel5;//updata面板  文本域，信息为需要修改的图书价格

    private JLabel jLabel6;//updata面板  文本域，信息为需要修改的图书数量

    private JLabel jLabel7;//updata面板  文本域，信息为需要修改的图书类型


    private JTextField textField3;//updata面板 文本框，信息为需要修改的图书名字

    private JTextField textField4;//updata面板  文本框，信息为需要修改的图书作者

    private JTextField textField5;//updata面板   文本框，信息为需要修改的图书价格

    private JTextField textField6;//updata面板  文本框，信息为需要修改的图书数量

    private JTextField textField7;//updata面板   文本框，信息为需要修改的图书类型



    //该窗口为修改图书信息的窗口
    void updata() {
        JFrame jframe = new JFrame();//创建窗口jframe




        JPanel jPanel5 = new JPanel();//创建面板5
        //jframe.getContentPane().setSize();
        jframe.setSize(750, 300);//设置窗体大小
        //jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//设置窗体关闭方式
        //jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭方式//不可释放会连锁反应
        jframe.setLocationRelativeTo(null);//窗口居中显示
        jframe.setResizable(false);//设置窗口是否可以改变大小
        jframe.setVisible(true);//设置窗体可见
        //jPanel5.setBackground(Color.RED); // 设置面板颜色为红色背景
        jframe.setLayout(null);//设置窗体布局为绝对布局

        bupdata2 = new JButton("确认修改");//创建"确认修改"按钮
        bupdata2.setFont(font2);//设置按钮字体大小格式
        bupdata2.setBounds(400, 159, 120, 35);//设置"确认修改"按钮位置大小
        jframe.add(bupdata2);//添加"确认修改"按钮到窗体


        jLabel3 = new JLabel("书籍名称修改为");//图书名称文本域
        jLabel3.setFont(font2);//设置文本域字体大小格式
        jLabel3.setBounds(10, 10, 220, 55);//设置文本域的出现位置以及大小
        jframe.add(jLabel3);//将文本域3添加到jframe窗体中

        textField3 = new JTextField();//updata面板 文本框，信息为需要修改的图书名字
        textField3.setFont(font2);//设置文本域字体大小格式
        textField3.setBounds(150, 22, 200, 35);//图书名字文本框位置大小设定   22
        textField3.setText(MainApp.staticdata.bokname);//设置文本框默认文字信息，此处为书的名称
        jframe.add(textField3);//将文本框3添加到jframe窗体中


        jLabel4 = new JLabel("书籍作者修改为");//图书作者文本域
        jLabel4.setFont(font2);//设置文本域字体大小格式
        jLabel4.setBounds(373, 10, 220, 55);//设置文本域的出现位置以及大小
        jframe.add(jLabel4);//将文本域4添加到jframe窗体中

        textField4 = new JTextField();//updata面板 文本框，信息为需要修改的图书作者
        textField4.setFont(font2);//设置文本域字体大小格式
        textField4.setBounds(500, 22, 200, 35);//图书作者文本框位置大小设定
        textField4.setText(MainApp.staticdata.bokauthor);//设置文本框默认文字信息，此处为书的作者
        jframe.add(textField4);//将文本框4添加到jframe窗体中


        jLabel5 = new JLabel("书籍价格修改为");//图书价格文本域
        jLabel5.setFont(font2);//设置文本域字体大小格式
        jLabel5.setBounds(10, 80, 220, 55);//设置文本域的出现位置以及大小   y+70
        jframe.add(jLabel5);//将文本域5添加到jframe窗体中

        textField5 = new JTextField();//updata面板 文本框，信息为需要修改的图书价格
        textField5.setFont(font2);//设置文本域字体大小格式
        textField5.setBounds(150, 92, 200, 35);//图书名字文本框位置大小设定   92   70   =162
        textField5.setText(MainApp.staticdata.bokprice.toString());//设置文本框默认文字信息，此处为书的价格
        jframe.add(textField5);//将文本框5添加到jframe窗体中
        jframe.repaint();


        jLabel6 = new JLabel("书籍数量修改为");//图书数量文本域
        jLabel6.setFont(font2);//设置文本域字体大小格式
        jLabel6.setBounds(373, 82, 220, 55);//设置文本域的出现位置以及大小
        jframe.add(jLabel6);//将文本域6添加到jframe窗体中

        textField6 = new JTextField();//updata面板 文本框，信息为需要修改的图书数量
        textField6.setFont(font2);//设置文本域字体大小格式
        textField6.setBounds(500, 92, 200, 35);//图书数量文本框位置大小设定
        textField6.setText((String.valueOf(MainApp.staticdata.bokamount)));//设置文本框默认文字信息，此处为书的作者
        jframe.add(textField6);//将文本框6添加到jframe窗体中


        jLabel7 = new JLabel("书籍类型修改为");//图书类型文本域
        jLabel7.setFont(font2);//设置文本域字体大小格式
        jLabel7.setBounds(10, 150, 220, 55);//设置文本域的出现位置以及大小
        jframe.add(jLabel7);//将文本域7添加到jframe窗体中

        textField7 = new JTextField();//updata面板 文本框，信息为需要修改的图书类型
        textField7.setFont(font2);//设置文本域字体大小格式
        textField7.setBounds(150, 162, 200, 35);//图书名字文本框位置大小设定
        textField7.setText(MainApp.staticdata.boktype);//设置文本框默认文字信息，此处为书的类型
        jframe.add(textField7);//将文本框7添加到jframe窗体中
        jframe.repaint();//重绘界面



        bupdata2.addMouseListener(new MouseListener() {//为修改按钮添加鼠标监听事件
            @Override
            public void mouseClicked(MouseEvent e) {//鼠标单击事件

               String BOKname = gain(textField3);//获取图书名称文本框中的内容

               String BOKauthor = gain(textField4);//获取图书作者文本框中的内容

//               String BOKprice = gain(textField5);
//               BigDecimal decimal = BigDecimal.valueOf(Double.parseDouble(BOKprice));
                BigDecimal BOKprice =   BigDecimal.valueOf(Double.parseDouble(gain(textField5)));//获取图书价格文本框中的内容




                //数量不能填写0以下数字
                int BOKamount =Integer.parseInt(gain(textField6));//获取图书数量文本框中的内容

                String BOKtype = gain(textField7);//获取图书类型文本框中的内容

               updata2(BOKname,BOKauthor,BOKprice,BOKamount,BOKtype);//传入参数以进行修改数据库信息

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //---



        jframe.repaint();//重绘界面

        jframe.addWindowListener(new WindowAdapter() {//添加窗体监听器

            public void windowClosing(WindowEvent e) {//窗口关闭时执行的代码
                super.windowClosing(e);
                MainApp.staticdata.countupdata = 0;  //countup重置为0
            }

        });





    }//updata  end


    String gain(JTextField textField) {//获得文本框的内容信息
        String text = textField.getText();//获取文本框中的内容信息
        //System.out.println("获取文本框内容");//提示信息
        return text;//返回文本框中的内容
    }



    private BigDecimal bokamount = BigDecimal.valueOf(0);
    //实现获得信息并将信息更新进数据库的方法
    void  updata2(String bookname,String bookauthor,BigDecimal bookprice,int bookamount,String booktype) {//修改数据库信息的方法，传入要修改成功的数据


        //void  updata2(String bookname) {

        //String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '%" + search + "%'";

        //String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '" + search + "'";
        //String sql2 = "select id form t_book where id = ?";
        //SELECT 字段1,字段2,...  from 表名 where name like '%豪'
        //String sql2 = "select id,bookname,bookauthor,bookprice,bookamount,booktype from t_book where id LIKE'" + Main.staticdata.upid + "'";
            String sql="update t_book set bookname='" + bookname + "' where id='"+ MainApp.staticdata.upid+"' ";//生成一条mysql语句
            String sql2="update t_book set bookauthor='" + bookauthor + "' where id='"+ MainApp.staticdata.upid+"' ";//生成一条mysql语句
            String sql3="update t_book set bookprice='" + bookprice + "' where id='"+ MainApp.staticdata.upid+"' ";//生成一条mysql语句
            String sql4="update t_book set bookamount='" + bookamount + "' where id='"+ MainApp.staticdata.upid+"' ";//生成一条mysql语句
            String sql5="update t_book set booktype='" + booktype + "' where id='"+ MainApp.staticdata.upid+"' ";//生成一条mysql语句

        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/booksystem";
        String user = "root";
        String password = "123456";


        {
            try {
                // 加载数据库驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 建立连接
                Connection con = DriverManager.getConnection(url, user, password);
                // 创建Statement
                Statement stmt = con.createStatement();

                //图书信息不能为空，测试异常
                if(bookname.equals("")||bookauthor.equals("")||bookprice.equals("")||booktype.equals("")){

                    System.out.println("图书价格不为空");
                    throw new Exception();
                }

                //图书数量与不为0：
                if(bookamount < 0||bookprice.compareTo(bokamount)<0){
                    throw new RuntimeException();
                }



                int rs = stmt.executeUpdate(sql);
                int rs2 = stmt.executeUpdate(sql2);
                int rs3 = stmt.executeUpdate(sql3);
                int rs4 = stmt.executeUpdate(sql4);
                int rs5 = stmt.executeUpdate(sql5);

//废弃代码残余
//                   if(rs3 != 1||rs4 != 1){//可有可无  ，没有作用的代码
//                       def();
//                   }


                if(rs == 1&&rs2 == 1&&rs3 == 1&&rs4 == 1&&rs5 == 1){//如果所有修改都成功了就执行以下代码，弹出成功窗口
                    System.out.println("修改成功");//在控制台输出"修改成功"
                        if(MainApp.staticdata.countwin == 0){//检查成功窗口是否为一个
                            win();
                        }
                }



                // 关闭资源
//                rs.close();
                stmt.close();
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            } catch (RuntimeException e) {
                amount();
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }





    }//updata2() end

    //修改成功后弹出的修改成功的消息窗
    void win(){
        MainApp.staticdata.countwin = 1;//本窗体是否已经存在以及只能存在一个该窗体的判断代码
        JButton win;//声明一个按钮
        JLabel jLabel ;//登录成功的提示
        JFrame jwin = new JFrame();//创建一个窗体对象
        jwin.setLayout(null);//设置窗体布局管理器为绝对布局
        jwin.setSize(200, 140);//设置窗体大小
        jwin.setLocationRelativeTo(null);//窗口居中显示
        jwin.setResizable(false);//设置窗口是否可以改变大小
        jLabel = new JLabel("修改成功");//实例化文本域
        win = new JButton("确定");//实例化一个按钮
        win.setFont(font2);//设置按钮字体大小样式
        jLabel.setFont(font2);//设置文本域字体大小样式
        jLabel.setBounds(56, 0, 180, 40);//设置成功信息文字的大小和位置
        win.setBounds(42, 44, 100, 30);//设置确定按钮的大小和位置
        jwin.add(win);//添加确认按钮到窗体
        jwin.add(jLabel);//将文本域加入到成功窗体中
        jwin.setVisible(true);//设置弹出的窗体可见
        win.addMouseListener(new MouseListener() {//定义修改成功窗口中按钮的监听事件
            @Override
            public void mouseClicked(MouseEvent e) {//鼠标单击事件
                jwin.dispose();//jwin窗体关闭
                MainApp.staticdata.countwin = 0;//将验证是否存在此窗口的判断重置为0，方便下次再次弹出成功信息窗口
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    //设置窗体关闭执行的代码
        jwin.addWindowListener(new WindowAdapter() {//添加窗体监听器

            public void windowClosing(WindowEvent e) {//窗口关闭时执行的代码
                super.windowClosing(e);
                MainApp.staticdata.countwin = 0;  //countup重置为0
            }

        });

    }   //win  end

    //修改失败后弹出的修改失败信息
    void def(){
        MainApp.staticdata.countdef = 1;
        JButton def;
        JLabel jLabel ;//登录成功的提示
        JFrame jdef = new JFrame();
        jdef.setTitle("修改图书信息成功");
        jdef.setLayout(null);
        jdef.setSize(400,280);
        jdef.setLocationRelativeTo(null);//窗口居中显示
        jdef.setResizable(false);//设置窗口是否可以改变大小
        jLabel = new JLabel("点击修改无反应为修改失败");
        def = new JButton("确定");
        def.setFont(font2);
        jLabel.setFont(font2);
        jLabel.setBounds(12,-28,380,200);//设置成功信息文字的大小和位置
        def.setBounds(96,134,180,55);//设置确定按钮的大小和位置
        jdef.add(def);
        jdef.add(jLabel);
        jdef.setVisible(true);
        def.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jdef.dispose();
                MainApp.staticdata.countdef = 0;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //设置窗体关闭执行的代码
        jdef.addWindowListener(new WindowAdapter() {//添加窗体监听器

            public void windowClosing(WindowEvent e) {//窗口关闭时执行的代码
                super.windowClosing(e);
                MainApp.staticdata.countdef = 0;  //countup重置为0
            }

        });

    }   //win  end


    //图书数量不为负数：
    void amount(){

        JFrame jframeamount = new JFrame(); //图书数量不为负数，提示框
        //图书数量提示框修改内容：
        jframeamount.setSize(410, 200);//设置窗体大小
        jframeamount.setTitle("数量内容不为负");
        jframeamount.setLocationRelativeTo(null);//窗口居中显示
        jframeamount.setResizable(false);//设置窗口是否可以改变大小
        jframeamount.setLayout(null);//设置窗体布局为绝对布局
        jframeamount.setVisible(true);//设置窗体可见
        jframeamount.setAlwaysOnTop(true);//设置窗口前置
        jLabel = new JLabel("图书数量与价格不能为负数");//实例化文本域信息，并初始化信息内容
        jLabel.setFont(font2);//修改提示信息文本域的字体大小格式
        jLabel.setBounds(10,15,400,55);//设置信息文本域出现的位置以及大小
        jframeamount.add(jLabel);//添加文本域到此窗口
        bdef = new JButton("确认信息");//实例化按钮对象并初始化按钮信息
        bdef.setFont(font2);//设置按钮文字大小格式
        bdef.setBounds(100,75,180,55);
        jframeamount.add(bdef);

        //按下按钮关闭窗口
        bdef.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //按钮单击事件
                jframeamount.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }


}
