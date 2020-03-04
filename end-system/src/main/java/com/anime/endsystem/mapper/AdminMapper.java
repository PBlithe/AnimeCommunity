package com.anime.endsystem.mapper;

import com.anime.endsystem.model.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

public interface AdminMapper {

    @Update("UPDATE t_admin SET auth = #{auth} WHERE id = #{id}")
    void updateAuthById(String auth ,String id) throws DataAccessException;//修改账号

    @Delete("DELETE FORM t_admin WHERE id = #{id}")
    void deleteAdminById(String id) throws DataAccessException ;//删除账号

    @Insert("INSERT INTO t_admin(id,password,auth,optor) VALUES(#{id},HEX(AES_ENCRYPT(#{password},'ANIME-ADMIN')),#{auth},#{optor})")
    void addAdmin(Admin admin) throws DataAccessException;

    @Select("SELECT auth FROM t_admin WHERE id=#{id}")
    String findAuthById(String id);
}
