package com.book.one;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.*;

//See2类为此功能的第二个版本,2版本面板2使用灰色，表示标准版本
public class FindBook extends JFrame { //此See2为see查询图书功能的2.0版本
    private String booktext;//查询文本框中的内容
    private JTextField textField;//声明图书查询的文本框
    private JPanel jPanel2;//声明面板2

    private JButton bse;//查询图书按钮
    private JTable jtable;//声明表格
    //Font font2 = new Font("Serif", Font.BOLD, 28);//设置文字大小
    Font font2 = new Font("Serif", Font.BOLD, 17);//设置文字大小

    void see2() {

        jPanel2 = new JPanel();//创建面板2
//        jPanel = new JPanel(); //创建面板1
        MainApp.staticdata.count = 1;//设置该窗口是否存在，这里赋值为0，表示该窗口已经存在

        jtable = new JTable();//创建数据表格
        this.setSize(570, 610);//查询窗口的大小
        this.setTitle("图书查询");//设置窗口标题
        this.setLayout(null);//设置窗口布局
        this.setLocationRelativeTo(null);//窗口居中显示
        setResizable(false);//设置窗口是否可以改变大小


        bse = new JButton("模糊查询");//图书查询按钮创建，并赋值"图书查询"
        textField = new JTextField();  //文本框创建，用于输入图书名称信息

        bse.setFont(font2);//查询按钮文字采用28号
        textField.setFont(font2);//图书查询窗口文字大小使用28号字体

        textField.setBounds(10, 30, 150, 35);//图书查询文本框位置大小设定
        bse.setBounds(170, 30, 150, 35);//图书查询按钮位置大小设定
        jtable.setBounds(300,0,800,250);//设置表格的大小以及位置


        add(textField);//添加文本框到面板,文本框为 填写的查询图书信息
        add(bse);//按钮添加到面板1容器,查询按钮



        jPanel2.setBounds(10,100,500,430);//设置面板2的大小以及位置
        jPanel2.add(jtable);//添加表格到面板2


        seeall();//默认显示所有图书信息

        //以下代码极其重要，是将表格面板添加至窗口的重要代码
        getContentPane().add(jPanel2);//得到当前哦那个其，把jpanel2添加到容器中***极其重要的代码
        setVisible(true);//设置窗体可见

        repaint();

        bse.addMouseListener(new MouseListener() {//为查询按钮添加鼠标监听器
            @Override
            public void mouseClicked(MouseEvent e) {//鼠标点击事件
                jPanel2.removeAll();//移除面板2中所有的内容
                System.out.println(gain(textField));//输出显示查询文本框中的信息
                booktext = gain(textField);//使用gain方法获得文本框中的内容信息并赋值给booktext
                seeall2(booktext);//将booktext传入seeall2方法中
                repaint();//窗体重绘
                setVisible(true);//设置窗口内容可见


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

        setVisible(true);//设置窗口内容可见

        this.addWindowListener(new WindowAdapter() {//窗口关闭执行的代码


            public void windowClosing(WindowEvent e) {//关闭窗口时执行的事件
                super.windowClosing(e);
                MainApp.staticdata.count = 0;//设置该窗口是否存在，这里重置为0，方便下次创建
            }

        });


    }

    //获取文本框中的内容，此处为工具类
    private String gain(JTextField textField) {//获取文本框中的文本信息的方法
        String text = textField.getText();//获取文本框中的内容信息
        //System.out.println("获取文本框内容");//提示信息
        return text;//返回框中的内容

    }


    void seeall() {//默认显示所有图书信息

        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/booksystem";
        String user = "root";
        String password = "123456";

        // 表格列名
        String[] columnNames = {"ID", "书名", "作者", "价格", "数量", "类型"};

        // 创建表格的数据模型
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);//创建表格模型
        {
            try {
                // 加载数据库驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 建立连接
                Connection con = DriverManager.getConnection(url, user, password);
                // 创建Statement
                Statement stmt = con.createStatement();
                String sql = "select * from t_book";
                // 执行查询
                //ResultSet rs = stmt.executeQuery("select * from t_book");//原始语句
                ResultSet rs = stmt.executeQuery(sql);//执行sql代码，并返回信息到rs

                // 遍历结果集
                while (rs.next()) {//循环遍历rs得到的信息
                    // 根据列名获取数据
                    int id = rs.getInt("ID");
                    String name = rs.getString("BookName");
                    String author = rs.getString("BookAuthor");
                    BigDecimal price = rs.getBigDecimal("bookprice");
                    int amount = rs.getInt("bookamount");
                    String BookType = rs.getString("BookType");

                    // 将数据添加到表格模型
                    model.addRow(new Object[]{id, name, author, price, amount, BookType});//添加到表格模型中

                }

                // 关闭资源
                rs.close();
                stmt.close();
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        }
        jtable = new JTable(model);//表格模型添加到表
        jPanel2.add(new JScrollPane(jtable),BorderLayout.CENTER); // 使用滚动面板以防表格太大****


    }// seeall()方法  end


    void seeall2(String search) {//根据传入的字符串模糊查询图书
//        time = 1;

        String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '%" + search + "%'";
        //String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '" + search + "'";
        //实验成功  ：：String sql2 = "SELECT * FROM t_book WHERE bookname LIKE '"+ search +"'";
        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/booksystem";
        String user = "root";
        String password = "123456";

        // 表格列名
        String[] columnNames = {"ID", "书名", "作者", "价格", "数量", "类型"};

        // 创建表格的数据模型
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        {
            try {
                // 加载数据库驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 建立连接
                Connection con = DriverManager.getConnection(url, user, password);
                // 创建Statement
                Statement stmt = con.createStatement();
                //String sql = "select * from t_book";
                // 执行查询
                //ResultSet rs = stmt.executeQuery("select * from t_book");//原始语句
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

                    // 将数据添加到表格模型
                    model.addRow(new Object[]{id, name, author, price, amount, BookType});//添加数据信息到表格模型

                }

                // 关闭资源
                rs.close();
                stmt.close();
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        }
        jtable = new JTable(model);//表格模型添加到表
        jPanel2.add(new JScrollPane(jtable)); // 使用滚动面板以防表格太大****



    }//seeall2() end


}
