package com.web.SM_Barber;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SmRepository extends JpaRepository<SmModel, Integer>{
	
	@Query(nativeQuery = true, value = "sp_fetch")
	public List<SmModel> sp_fetch();
	
	@Query(nativeQuery = true, value = "sp_search :id")
	public SmModel sp_search(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "SELECT * from tbl_register where email = :email AND password = :password")
	public SmModel sp_login(@Param("email") String id , @Param("password") String password);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "sp_register :fname , :lname , :email , :password , :phoneno , :gender , :selecttime , :description")
	public void sp_register(@Param("fname") String fname, @Param("lname") String lname, @Param("email") String email, @Param("password") String password, @Param("phoneno") String phoneno, @Param("gender") String gender, @Param("selecttime") String selecttime, @Param("description") String description);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "sp_update :id , :fname , :lname , :email , :password , :phoneno , :gender , :selecttime , :description")
	public void sp_update(@Param("id") int id, @Param("fname") String fname, @Param("lname") String lname, @Param("email") String email, @Param("password") String password, @Param("phoneno") String phoneno, @Param("gender") String gender, @Param("selecttime") String selecttime, @Param("description") String description);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "sp_delete :id")
	public void sp_delete(@Param("id") int id);
}
