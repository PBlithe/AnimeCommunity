package com.anime.endsystem.mapper;

import com.anime.endsystem.enums.AdminState;
import com.anime.endsystem.model.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AdminMapper {

    @Update("UPDATE t_admin SET auth = #{auth} WHERE id = #{id}")
    void updateAuthById(String auth ,String id) throws DataAccessException;//修改账号

    @Update("UPDATE t_admin SET state = #{state} WHERE id = #{id}")
    void updateState(AdminState state, String id);

    @Delete("DELETE FROM t_admin WHERE id = #{id}")
    void deleteAdminById(String id) throws DataAccessException ;//删除账号

    @Insert("INSERT INTO t_admin(id,password,auth,optor) VALUES(#{id},HEX(AES_ENCRYPT(#{password},'ANIME-ADMIN')),#{auth},#{optor})")
    void addAdmin(Admin admin) throws DataAccessException;//添加一个超级管理员

    @Select("SELECT auth FROM t_admin WHERE id=#{id}")
    String findAuthById(String id);//根据ID查询权限字符串

    @Select("SELECT * FROM t_admin ORDER BY time LIMIT #{start},#{end}")
    List<Admin> findAdminByLimit(int start, int end);//查询管理员根据LIMIT

    @Select("SELECT COUNT(*) FROM t_admin")
    int findCount();

    @Select("SELECT * FROM t_admin WHERE id != #{id} ORDER BY time LIMIT #{start},#{end}")
    List<Admin> findAdminByIdAndLimit(int start, int end,String id);

    @Select("SELECT * FROM t_admin WHERE id = #{id}")
    Admin findAdminById(String id);
}
