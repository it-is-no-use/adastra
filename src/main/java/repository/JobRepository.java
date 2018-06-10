package repository;

import entity.Job;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

	//10 последних записей в таблице job
	@Query(value = "select * from job order by j_id desc limit 10", nativeQuery = true)
	List<Job> find10();
	
	//записи из таблицы job в промежуток времени
	@Query(value = "select * from job where j_when between ?1 and ?2", nativeQuery = true)
	List<Job> findWhen(Date date1, Date date2);
	
	//на складе сейчас
	@Query(value = "select m.mat_name, sum(j.weight) from job j inner join materials m on m.mat_id=j.mat_id group by m.mat_name", nativeQuery = true)
	Object[][] find_storage_now();
	
	//на складе на определенный день
	@Query(value = "select m.mat_name, sum(j.weight) from job j inner join materials m on m.mat_id=j.mat_id where j.j_when<=?1 group by m.mat_name", nativeQuery = true)
	Object[][] find_storage_moment(Date date);
	
	//на складе в промежуток времени
	@Query(value = "select m.mat_name, sum(j.weight) from job j inner join materials m on m.mat_id=j.mat_id where j.j_when between ?1 and ?2 group by m.mat_name", nativeQuery = true)
	Object[][] find_storage_interval(Date date1,Date date2);
	
	//приход в определенный момент
	@Query(value = "select mat_name, sum(weight) from job j inner join materials m on (j.mat_id=m.mat_id) where (j.j_when between ?1 and ?2) and (j.ob_id=5) group by mat_name", nativeQuery = true)
	Object[][] find_storage_coming(Date date1, Date date2);

	//расход в определенный момент
	@Query(value = "select mat_name, sum(abs(weight)) from job j inner join materials m on j.mat_id=m.mat_id where (j.j_when between ?1 and ?2) and (j.ob_id!=5) group by mat_name", nativeQuery = true)
	Object[][] find_storage_consumption(Date date1, Date date2);
	
	//траты на объектах 
	@Query(value = "select o.ob_name, m.mat_name, sum(abs(j.weight)) from job j inner join materials m on j.mat_id=m.mat_id inner join objects o on j.ob_id=o.ob_id where o.ob_id!=5 group by o.ob_name, m.mat_name", nativeQuery = true)
	Object[][][] find_spending_now();
	
	//траты на объектах 
	@Query(value = "select o.ob_name, m.mat_name, sum(abs(j.weight)) from job j inner join materials m on j.mat_id=m.mat_id inner join objects o on j.ob_id=o.ob_id where (j.j_when between ?1 and ?2) and (o.ob_id!=5) group by o.ob_name, m.mat_name", nativeQuery = true)
	Object[][][] find_spending_interval(Date date1, Date date2);
}