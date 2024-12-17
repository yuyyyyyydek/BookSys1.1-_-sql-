package com.book.one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.*;

public class DeleteBook extends JFrame {

    private String booktext;//接收文本框中的内容

    //Font font2 = new Font("Serif", Font.BOLD, 28);//设置文字大小
    Font font2 = new Font("Serif", Font.BOLD, 17);//设置文字大小

    private JButton bdelse;//查询删除图书信息按钮，点击后查询到图书的名称

    private JButton bdel;//确认删除图书信息按钮，点击后删除显示出的图书

    private JLabel jLabel;//删除书籍的id 的文本域信息

    private JLabel jLabel2;//删除书籍的图书名称 的文本域信息

    private JTextField textField;//声明删除图书ID的文本框，用来填写图书ID

    private JTextField textField2;//声明删除图书名称的文本框，用来显示图书名称信息

    void del(){
        setSize(550,180);//设置窗体大小  710,350
        setTitle("图书删除");
        setLocationRelativeTo(null);//窗口居中显示
        setResizable(false);//设置窗口是否可以改变大小
        setLayout(null);//设置窗体布局为绝对布局
        setVisible(true);//设置窗体可见
        //setAlwaysOnTop(true);//设置窗口前置,不必前置
        jLabel = new JLabel("要删除的图书ID");//文本域 提示信息,请填入图书ID以确认
        jLabel2 = new JLabel("要删除的图书名为");//文本域 提示信息,请填入图书ID以确认
        jLabel.setFont(font2);//设置文本域字体大小,信息内容为：填写删除图书的ID提示信息
        jLabel2.setFont(font2);//设置文本域字体大小，信息内容为：显示删除图书的图书名称
        jLabel.setBounds(10,10,400,55);//设置文本域提示信息位置大小
        jLabel2.setBounds(10,80,400,55);
        add(jLabel2);
        add(jLabel);//添加文本域至本窗口
        textField = new JTextField();//实例化文本框，此文本框用于填写图书ID
        textField.setFont(font2);//设置文本框的文字大小格式
        textField.setBounds(158,26,160,25);
        textField2 = new JTextField("此处显示图书名称");
        textField2.setVisible(false);
        textField2.setFont(font2);//设置文本框的文字大小格式
        textField2.setBounds(170,95,160,25);
        //setLocation(58,65);
        add(textField);//添加文本域到窗口，信息为：要输入的图书ID
        add(textField2);//添加文本域到窗口  信息为：显示要删除图书的图书名称
        bdelse = new JButton("查询删除图书");//实例化按钮对象,按钮信息为:显示图书名称
        bdelse.setFont(font2);//设置按钮文字大小格式
        bdelse.setBounds(333,21,180,35);//设置按钮位置大小，默认的按钮大小为180 65
        bdel = new JButton("删除图书");//实例化按钮对象，按钮信息为：确认删除
        bdel.setFont(font2);//设置按钮文字大小格式
        bdel.setBounds(339,85,180,35);
        bdel.setEnabled(false);//设置"确认修改"按钮不可用,除非查出来了书籍信息
        add(bdel);
        add(bdelse);
        repaint();

        //显示图书名称按下后  需要激活"确认删除"按钮,并且要查询到图书的图书名称
        bdelse.addMouseListener(new MouseListener() {//为鼠标添加监听器
            @Override
            public void mouseClicked(MouseEvent e) {//鼠标单击事件，鼠标单击后执行的代码

                booktext = gain(textField);//使用gain方法获得文本框中的内容信息并赋值给booktext，删除图书ID的文本框

                //Object object = updata(booktext);//将booktext传入seeall2方法中
                updata(booktext);//获得需要查询的文本框内容并进行数据库查找,查找书籍的ID
                textField2.setText(MainApp.staticdata.bokname2);//修改文本框内容，内容为要修改的书籍名称
                //bokname2 ="";//用于获取要删除的图书的书名//要删除的图书书名


                if(MainApp.staticdata.bokname2 != ""){  //当静态字符串bokname2不为空，bokname2有内容时进行此判断
                    textField2.setVisible(true);
                    bdel.setEnabled(true);//修改"删除图书信息"按钮进入激活可按状态


                }
                //setVisible(true);//设置窗口内容可见

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

        //按下删除图书按钮，删除图书
        bdel.addMouseListener(new MouseListener() {//为删除图书按钮添加鼠标监听器
            @Override
            public void mouseClicked(MouseEvent e) {//鼠标单击事件

                updata2();//传入参数以进行删除数据库信息

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


        //删除界面关闭后，是否存在一个删除窗口的静态变量要恢复为0，方便下次判断
        this.addWindowListener(new WindowAdapter() {//添加窗体监听器

            public void windowClosing(WindowEvent e) {//窗口关闭时执行的代码
                super.windowClosing(e);
                MainApp.staticdata.countdel = 0;  //countup重置为0
            }

        });


    }


    private String gain(JTextField textField) {//获取文本框中的文本信息的方法
        String text = textField.getText();//获取文本框中的内容信息
        //System.out.println("获取文本框内容");//提示信息
        return text;//返回框中的内容

    }


    void  updata(String search) {//传入要查询的ID，进行数据库查询，并显示


        //String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '%" + search + "%'";

        //String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '" + search + "'";
        //String sql2 = "select id form t_book where id = ?";
        //SELECT 字段1,字段2,...  from 表名 where name like '%豪'
        String sql2 = "select id,bookname,bookauthor,bookprice,bookamount,booktype from t_book where id LIKE'" + search + "'";


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

                ResultSet rs = stmt.executeQuery(sql2);


                // 遍历结果集
                while (rs.next()) {

                    // 根据列名获取数据
                    int id = rs.getInt("ID");
                    String name = rs.getString("BookName");
                    String author = rs.getString("BookAuthor");
                    BigDecimal price = rs.getBigDecimal("bookprice");
                    int amount = rs.getInt("bookamount");
                    String BookType = rs.getString("BookType");

                    //*****System.out.println(name);


                    System.out.println(id);
                    MainApp.staticdata.deid2 = id;//查询到的id 写入到静态类的属性中这里是deid2
                    System.out.println(name);
                    MainApp.staticdata.bokname2 = name;//这里是bokname2。查询到的书名写入到静态类的属性中
                    System.out.println(author);
                    MainApp.staticdata.bokauthor = author;//查询到的图书名称写入到静态类的属性中
                    System.out.println(price);
                    MainApp.staticdata.bokprice = price;//查询到的图书价格写入到静态类的属性中
                    System.out.println(amount);
                    MainApp.staticdata.bokamount = amount;//查询到的图书数量写入到静态类的属性中
                    System.out.println(BookType);
                    MainApp.staticdata.boktype = BookType;//查询到的图书类型写入到静态类的属性中
                    // 将数据添加到表格模型
                    //model.addRow(new Object[]{id, name, author, price, amount, BookType});


                    ;

                }

                // 关闭资源
                rs.close();
                stmt.close();
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        }


    }//updata() end


    void  updata2() {//删除数据库信息的方法，传入要删除的数据信息，根据静态变量ID查找



        String sql="delete from t_book where id='"+ MainApp.staticdata.deid2+"' ";//生成一条mysql语句


        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/booksystem";
        String user = "root";
        String password = "123456";



            try {
                // 加载数据库驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 建立连接
                Connection con = DriverManager.getConnection(url, user, password);
                // 创建Statement
                Statement stmt = con.createStatement();




                int rs = stmt.executeUpdate(sql);


                if(rs == 1){//如果所有删除成功了就执行以下代码，弹出成功窗口
                    System.out.println("删除成功");//在控制台输出"修改成功"
                    if(MainApp.staticdata.countdewin == 0){//检查成功窗口是否为一个
                        win();
                    }
                }



                // 关闭资源
//                rs.close();
                stmt.close();
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();


        }




    }//updata2() end



    //修改成功后弹出的修改成功的消息窗
    void win(){
        MainApp.staticdata.countdewin = 1;//本窗体是否已经存在以及只能存在一个该窗体的判断代码
        JButton win;//声明一个按钮
        JLabel jLabel ;//登录成功的提示
        JFrame jwin = new JFrame();//创建一个窗体对象
        jwin.setLayout(null);//设置窗体布局管理器为绝对布局
        jwin.setSize(200, 140);//设置窗体大小
        jwin.setLocationRelativeTo(null);//窗口居中显示
        jwin.setResizable(false);//设置窗口是否可以改变大小
        jLabel = new JLabel("删除成功");//实例化文本域
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
                MainApp.staticdata.countdewin = 0;//将验证是否存在此窗口的判断重置为0，方便下次再次弹出成功信息窗口
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
                MainApp.staticdata.countdewin = 0;  //countup重置为0
            }

        });

    }   //win  end


}
