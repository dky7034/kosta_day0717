package com.kosta.dao;

import com.kosta.db.ConnectionProvider;
import com.kosta.vo.GoodsVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GoodsDAO {
    public int insert(GoodsVO g) {
        int re = -1;
        String sql = "INSERT INTO GOODS VALUES(SEQ_GOODS.NEXTVAL,?,?,?,?)";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, g.getItem());
            pstmt.setInt(2, g.getPrice());
            pstmt.setInt(3, g.getQty());
            pstmt.setString(4, g.getFname());
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return re;
    }

    public ArrayList<GoodsVO> findAll() {
        ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
        String sql = "SELECT * FROM GOODS";
        try {
            Connection conn = ConnectionProvider.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new GoodsVO(rs.getInt(1),
                                        rs.getString(2),
                                        rs.getInt(3),
                                        rs.getInt(4),
                                        rs.getString(5)));
            }
            ConnectionProvider.close(rs, stmt, conn);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return list;
    }

    public GoodsVO findByNo(int no) {
        GoodsVO g = null;
        String sql = "SELECT * FROM GOODS WHERE NO = ?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                g = new GoodsVO(rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5));
            }
            ConnectionProvider.close(rs, pstmt, conn);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return g;
    }

    public int update(GoodsVO g) {
        int re = -1;
        String sql = "UPDATE GOODS SET ITEM=?, PRICE=?, QTY=?, FNAME=? WHERE NO=?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, g.getItem());
            pstmt.setInt(2, g.getPrice());
            pstmt.setInt(3, g.getQty());
            pstmt.setString(4, g.getFname());
            pstmt.setInt(5, g.getNo());
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return re;
    }

    public int delete(int no) {
        int re = -1;
        String sql = "DELETE GOODS WHERE NO=?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return re;
    }
}
