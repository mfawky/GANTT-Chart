package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {


    public static Process[] WhatIsNext(Process P1,Process P2,Process P3,Process P4, Process P5, int VirAx) {
        // 1-step : filter ready
        int index = 0;
        Process[] array = new Process[6];
        if ( ( P1.ArrivalTime <= VirAx ) && P1.BurstTime > 0) {
            array[index] = P1;
            index++;
        }
        if (( P2.ArrivalTime <= VirAx) && P2.BurstTime > 0) {
            array[index] = P2;
            index++;
        }
        if ( ( P3.ArrivalTime <= VirAx ) && P3.BurstTime > 0) {
            array[index] = P3;
            index++;
        }
        if ( ( P4.ArrivalTime <= VirAx ) && P4.BurstTime > 0) {
            array[index] = P4;
            index++;
        }
        if ( ( P5.ArrivalTime <= VirAx ) && P5.BurstTime > 0) {
            array[index] = P5;
            index++;
        }


        int lowestBurstTime = 0;
        Process temp;
        for (int i = 1;i < index;i++) {
            if(array[lowestBurstTime].BurstTime > array[i].BurstTime) {
                temp = array[i];
                array[i] = array[lowestBurstTime];
                array[lowestBurstTime] = temp;
            }
        }
        Process fake = new Process(0,-1);
        array[index] = fake;
        return array;
    }

    public static void main(String[] args) {
        JLabel P1 = new JLabel("Process 1 -> Red Box");
        P1.setFont(new Font(Font.MONOSPACED, Font.BOLD , 18));
        JLabel P2 = new JLabel("Process 2 -> Blue Box");
        P2.setFont(new Font(Font.MONOSPACED, Font.BOLD , 18));
        JLabel P3 = new JLabel("Process 3 -> Green Box");
        P3.setFont(new Font(Font.MONOSPACED, Font.BOLD , 18));
        JLabel P4 = new JLabel("Process 4 -> Gray Box");
        P4.setFont(new Font(Font.MONOSPACED, Font.BOLD , 18));
        JLabel P5 = new JLabel("Process 5 -> Black Box");
        P5.setFont(new Font(Font.MONOSPACED, Font.BOLD , 18));

        JLabel zero = new JLabel("0");
        zero.setFont(new Font(Font.MONOSPACED, Font.BOLD , 18));
        zero.setBounds(40,190,40,20);

        P1.setBounds(100,20,500,20);
        P2.setBounds(100,40,500,20);
        P3.setBounds(100,60,500,20);
        P4.setBounds(100,80,500,20);
        P5.setBounds(100,100,500,20);


        JFrame frame = new JFrame("GANTT CHART");
        int x = 100;
        final int y= 150;

        frame.add(P1);
        frame.add(P2);
        frame.add(P3);
        frame.add(P4);
        frame.add(P5);
        frame.add(zero);

        frame.setSize(850,600);
        frame.setLayout(null);
     //   frame.getContentPane().setBackground(new Color(0x56448));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(320,50);
        frame.setResizable(false);

        int VirtualAxis = 0;
        Process p1 = new Process(2,6);
        Process p2 = new Process(5,2);
        Process p3 = new Process(1,8);
        Process p4 = new Process(0,3);
        Process p5 = new Process(4,4);

        int xx = 40;
        int WidthPerSecond = 30;

        int prevProcess = 0;
        int test = 0;
        while ((p1.BurstTime != 0) || (p2.BurstTime != 0) || (p3.BurstTime != 0) || (p4.BurstTime != 0) || (p5.BurstTime != 0)) {

            Process[] proc = WhatIsNext(p1, p2, p3, p4, p5, VirtualAxis);
            proc[0].BurstTime--;

            int processNow = 0;
            if (proc[0] == p1 && prevProcess == 0)
                prevProcess = 1;
            else if (proc[0] == p2 && prevProcess == 0)
                prevProcess = 2;
            else if (proc[0] == p3 && prevProcess == 0)
                prevProcess = 3;
            else if (proc[0] == p4 && prevProcess == 0)
                prevProcess = 4;
            else if (proc[0] == p5 && prevProcess == 0)
                prevProcess = 5;

            if (proc[0] == p1)
                processNow = 1;
            else if (proc[0] == p2)
                processNow = 2;
            else if (proc[0] == p3)
                processNow = 3;
            else if (proc[0] == p4)
                processNow = 4;
            else if (proc[0] == p5)
                processNow = 5;


// (GUI) =============================================================================
            if(proc[0] == p1) {
                JLabel pro = new JLabel();
                pro.setOpaque(true);
                pro.setBackground(Color.red);
                pro.setBounds(xx, y, WidthPerSecond, 40);
                frame.add(pro);
            }
            else if (proc[0] == p2) {
                JLabel pro = new JLabel();
                pro.setOpaque(true);
                pro.setBackground(Color.BLUE);
                pro.setBounds(xx, y, WidthPerSecond, 40);
                frame.add(pro);
            }
            else if (proc[0] == p3) {
                JLabel pro = new JLabel();
                pro.setOpaque(true);
                pro.setBackground(Color.green);
                pro.setBounds(xx, y, WidthPerSecond, 40);
                frame.add(pro);
            }
            else if (proc[0] == p4) {
                JLabel pro = new JLabel();
                pro.setOpaque(true);
                pro.setBackground(Color.gray);
                pro.setBounds(xx, y, WidthPerSecond, 40);
                frame.add(pro);
            }
            else if (proc[0] == p5) {
                JLabel pro = new JLabel();
                pro.setOpaque(true);
                pro.setBackground(Color.BLACK);
                pro.setBounds(xx, y, WidthPerSecond, 40);
                frame.add(pro);
            }
// (GUI) =============================================================================


            for (int temp = 1; proc[temp].BurstTime != -1; temp++)
                proc[temp].WaitTime++;
            if (prevProcess != processNow || VirtualAxis == 22) {
                    String sp;
                    int temp;
                if (VirtualAxis == 22) {
                    temp = + VirtualAxis + 1;
                    sp = "" + temp;
                }
                else {
                    temp = 0;
                    sp = "" + VirtualAxis;
                }
                JLabel shiftPoint = new JLabel(sp);
                if (temp == 23)
                    shiftPoint.setBounds(xx + WidthPerSecond - 12,y+ 40,20,20);
                else
                    shiftPoint.setBounds(xx - 5,y+ 40,20,20);
                frame.add(shiftPoint);
                System.out.println(prevProcess);
            }
            prevProcess = processNow;


            VirtualAxis++;

            if (VirtualAxis == (p1.BurstTime + p2.BurstTime + p3.BurstTime + p4.BurstTime + p5.BurstTime ) ) {
                JLabel FINAL = new JLabel("" + VirtualAxis);
                FINAL.setBounds(xx - 5, y + 40, 20,20);
                frame.add(FINAL);
            }
            xx+= WidthPerSecond;
        }


        JLabel waitP1 = new JLabel("Waiting Time of Process 1  = "+ p1.WaitTime  + " ms");
        JLabel waitP2 = new JLabel("Waiting Time of Process 2  = "+ p2.WaitTime  + " ms" );
        JLabel waitP3 = new JLabel("Waiting Time of Process 3  = "+ p3.WaitTime  + " ms");
        JLabel waitP4 = new JLabel("Waiting Time of Process 4  = "+ p4.WaitTime  + " ms");
        JLabel waitP5 = new JLabel("Waiting Time of Process 5  = "+ p5.WaitTime  + " ms");
        double var = (double)  (p1.WaitTime + p2.WaitTime + p3.WaitTime + p4.WaitTime + p5.WaitTime) / 5;

        JLabel avgWaitTime = new JLabel("Average Waiting Time : (7 + 0 + 14 + 0 + 2)/5 = " + var + " ms");
        avgWaitTime.setFont(new Font(Font.MONOSPACED, Font.BOLD , 22));
        waitP1.setFont(new Font(Font.MONOSPACED, Font.PLAIN , 22));
        waitP2.setFont(new Font(Font.MONOSPACED, Font.PLAIN , 22));
        waitP3.setFont(new Font(Font.MONOSPACED, Font.PLAIN , 22));
        waitP4.setFont(new Font(Font.MONOSPACED, Font.PLAIN , 22));
        waitP5.setFont(new Font(Font.MONOSPACED, Font.PLAIN , 22));

        waitP1.setBounds(40,250 - 10,450,40);
        waitP2.setBounds(40,300 - 10,450,40);
        waitP3.setBounds(40,350 - 10,500,40);
        waitP4.setBounds(40,400 - 10,450,40);
        waitP5.setBounds(40,450 - 10,450,40);
        avgWaitTime.setBounds(40, 500, 750,40);

        frame.add(waitP1);
        frame.add(waitP2);
        frame.add(waitP3);
        frame.add(waitP4);
        frame.add(waitP5);
        frame.add(avgWaitTime);

        frame.setVisible(true);


    }

}
