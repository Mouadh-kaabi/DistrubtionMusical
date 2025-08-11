package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Details;
import com.example.demo.entities.Orange;

public interface DetailRepo extends JpaRepository<Details, Integer> {

	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`part_smart_globale`)),3) as part_smart_globale, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from orange,believe,deezer group by date1,date2 ")
	List<Object[]> statDateDetails();
	
	
	@Query(nativeQuery = true, value = "select *  from details where date1=:date1 and date2=:date2 and file=:file")
	List<Details> getDetailsByDate1andDate2andFile(@Param("date1") Date date1, @Param("date2") Date date2,
			@Param("file") String file);
	
	
	@Query(nativeQuery = true, value = "select DISTINCT count(date1) from details where date1=:date1 and file=:file \r\n")
    int getDetailsByDate1andFile(@Param("date1") Date date1, @Param("file") String file); 
	
	@Query(nativeQuery = true, value = "select  DISTINCT count(f.nom) from fournisseur  f  \n"
			+ "inner join users u  on f.user_id =u.id  \n"
			+ "inner join chanson c  on f.chanson_id =c.id \n"
			+ "where u.n_artistique=:namea \n"
			+ "and c.nom=:nomC")
int getFournisseurByArtisteBooleen (@Param("namea") String namea, @Param("nomC") String nomC);
	
	
	@Query(nativeQuery = true, value =  "select    f.nom from fournisseur  f  \n"
			+ "inner join user u  on f.user_id =u.id  \n"
			+ "inner join chanson c  on f.chanson_id =c.id \n"
			+ "where u.n_artistique=:namea \n"
			+ "and c.nom=:nomC")
String getFournisseurByArtiste (@Param("namea") String namea, @Param("nomC") String nomC);
	
	
	@Query(nativeQuery = true, value ="select  date1,date2, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end) as tax_telecom,\n"
			+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,\n"
			+ "(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from details  group by   date1,date2 ORDER BY round((sum(ttc)),3) desc  ") 
	List<Object[]>TotStatDate();
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from (\n"
			+ " select date1,date2, content,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ " (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom,\n"
			+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,\n"
			+ "(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from details    group by  content,namea \n"			
			+ "UNION ALL\n"
			+ "select  ch.nom as content,0 as date1, 0 as date2 , 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as part_artiste, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon )xx\n"
			+ "group by  content,namea order by round((sum(ttc)),3) desc ")
	List<Object[]> TotStatChanson();
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2, nom , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "date1 as date1,date2 as date2, ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
			+ "from details     \n"
			+ ")xx group by nom  order by round((sum(ttc)),3) desc")
	List<Object[]> TotStatArtiste();
	
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2, category,sub_category,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ " (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom,\n"
			+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,\n"
			+ "(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ " from details    group by  category order by round((sum(ttc)),3) desc")
	List<Object[]> TotStatcategory();
	
	
	@Query(nativeQuery = true, value = "select date1,file as name, round((sum(part_smart)),3) as value from details group by file order by round((sum(part_smart)),3) desc")
	List<Map<String,Object>> ChartsAdvancedPie();
	
	@Query(nativeQuery = true, value = "select date1,file as name, round((sum(part_smart)),3) as value from details group by file,date1 order by round((sum(part_smart)),3) desc")
	List<Map<String,Object>> ChartsAdvancedPieMulti();

	@Query(nativeQuery = true, value = "select  file as name, round((sum(part_artiste)),3) as value from details \n"
			+ " where namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')group by file ")
	List<Map<String,Object>> ChartsAdvancedPieById(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value = "select  date1,file as name, round((sum(part_artiste)),3) as value from details \n"
			+ " where namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')group by file,date1 ")
	List<Map<String,Object>> ChartsAdvancedPieMultiById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select  file as name, round((sum(part_artiste)),3) as value from details \n"
			+ " where    content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%')group by file ")
	List<Map<String,Object>> ChartsAdvancedPieByIdContent(@Param("id") Integer id);
	
	
	
	/*@Query(nativeQuery = true, value = "select date1,date2, namea , round((sum(ttc)),3) as tcc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,\n"
			+ " round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select  (select n_artistique FROM artiste u where u.id=:id)as namea,  date1,date2  round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  namea)xx group by namea ")*/
	
	@Query(nativeQuery = true, value = "select date1,date2, namea , round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht ,\n"
			+ " round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste, round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance from (\n"
			+ "select date1,date2, (select n_artistique FROM artiste u where u.id=:id)as namea,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht, \n"
			+ "round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste ,round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance \n"
			+ "from details  where   namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  namea )xx group by namea ")
	List<Object[]> TotStatArtisteById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n "
			+ "(case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom, \n"
			+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details  where   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> TotStatDateById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select  date1 , date2,  case when pays  is null then 'VIDE' else pays end as pays,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "  round(sum(part_artiste),3) as part_artiste \n"
			+ "from details  where   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by pays order by round((sum(ttc)),3) desc")
	List<Object[]> statPaysTotalByid(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from (\n"
			+ " select date1,date2, content,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ " (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom,\n"
			+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,\n"
			+ "(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from details  where     namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')  group by  content,namea \n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as date1,0 as date2, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as part_artiste, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ " where u.id =:id )xx\n"
			+ "group by  content,namea order by round((sum(ttc)),3) desc ")
		List<Object[]> TotStatChansonById(@Param("id") Integer id);
		
		
		@Query(nativeQuery = true, value = "select date1,date2, case when category  is null then 'VIDE' else category end as category , case when sub_category  is null then 'VIDE' else sub_category end as sub_category,  round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom, \n"
				+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste \n"
				+ "from details  where namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by  category order by round((sum(ttc)),3) desc")
		List<Object[]> TotStatCategoryById(@Param("id") Integer id);
		
		@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name, round((sum(part_artiste)),3) as value from details \n"
				+ "where  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsDateById(@Param("id") Integer id); 
		
		
		@Query(nativeQuery = true, value = "select  date_format(date1, '%m / %Y') as name, round((sum(part_smart)),3) as value from details group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsDate(); 
		
		@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name,file, round((sum(part_smart)),3) as value from details where file = 'Orange' group by date1 order by date1 desc limit 10")
		List<Map<String,Object>> ChartsDateOrange(); 
		
		
		@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name,file, round((sum(part_smart)),3) as value from details where file = 'TT' group by date1 order by date1 desc limit 10")
		List<Map<String,Object>> ChartsDateTT(); 
		
		@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name,file, round((sum(part_smart)),3) as value from details where file = 'Deezer' group by date1 order by date1 desc limit 10")
		List<Map<String,Object>> ChartsDateDeezer();
		
		
		@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name,file, round((sum(part_smart)),3) as value from details where file = 'Believe' group by date1 order by date1 desc limit 10")
		List<Map<String,Object>> ChartsDateBeleive();
		
		@Query(nativeQuery = true, value = "select  content as name, round((sum(quantite)),3) as value from details group by content order by round((sum(part_artiste)),3) desc limit 5")
		List<Map<String,Object>> ChartsChans();
		
		
		@Query(nativeQuery = true, value = "select content as name, round((sum(part_artiste)),3) as value from details \n"
				+ "where  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsChansonById(@Param("id") Integer id); 
		
		
		@Query(nativeQuery = true, value = "select content as name, round((sum(part_cp)),3) as value from details \n"
				+ "where    content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsChansonByIdCp(@Param("id") Integer id); 
		
		@Query(nativeQuery = true, value = "select content as name, round((sum(part_artiste)),3) as value from details where file = 'Believe' \n"
				+ "and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsChansonBelieveById(@Param("id") Integer id);
		
		
		
		@Query(nativeQuery = true, value = "select content as name, round((sum(part_artiste)),3) as value from details where file = 'Deezer' \n"
				+ "and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsChansonDeezerById(@Param("id") Integer id);
		
		
		@Query(nativeQuery = true, value = "select content as name, round((sum(part_artiste)),3) as value from details where file = 'Orange' \n"
				+ "and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsChansonOrangeById(@Param("id") Integer id); 
		
		@Query(nativeQuery = true, value = "select content as name, round((sum(part_artiste)),3) as value from details where file = 'TT' \n"
				+ "and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by date1 order by date1 desc limit 5")
		List<Map<String,Object>> ChartsChansonTTById(@Param("id") Integer id); 
		
		
		@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details where file = 'Orange' group by content order by round((sum(part_artiste)),3) desc limit 5")
		List<Map<String,Object>> ChartsChansOrange();
		
		
		@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details where file = 'TT' group by content order by round((sum(part_artiste)),3) desc limit 5")
		List<Map<String,Object>> ChartsChansTT();
		
		@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details where file = 'Deezer' group by content order by round((sum(part_artiste)),3) desc limit 5")
		List<Map<String,Object>> ChartsChansSDeezer();
		
		
		@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details where file = 'Believe' group by content order by round((sum(part_artiste)),3) desc limit 5")
		List<Map<String,Object>> ChartsChansBeleive();
		
		@Query(nativeQuery = true, value = "select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
				+ "from details group by  content order by  round(sum(ttc),3) desc limit 10")
		List<Object[]> topChanson();
		
		
		@Query(nativeQuery = true, value = "select name as value ,  round((sum(`value`)),3)    as name  \r\n"
				+ "from (\r\n"
				+ "select  (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))  \r\n"
				+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \r\n"
				+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \r\n"
				+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1)\r\n"
				+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \r\n"
				+ "else namea end)as name ,\r\n"
				+ "part_artiste  as value \r\n"
				+ "from details  \r\n"
				+ ")xx group by name \r\n"
				+ "order by name desc limit 6" )
		List<Map<String,Object>> chartsArts(); 
		
		
		
		@Query(nativeQuery = true, value = "select nom ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, paye\n" + "from (\n"
				+ "select    date1,date2,  (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
				+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
				+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
				+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
				+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
			    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
				+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
				+ "else namea end)as nom,namea,   \n"
				+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
				+ "from details   )xx group by date1,date2,nom,paye")
		List<Object[]> statRevenuTotal();
		
		@Modifying
		@Transactional
		@Query(nativeQuery = true, value = "update details d set d.paye = 1\n"
				+ "where d.namea  like concat(:namea,'%') and d.date1=:date1 and d.date2=:date2")
		public void paiementParMois(@Param("namea") String namea, @Param("date1") Date date1, @Param("date2") Date date2);





		@Modifying
		@Transactional
		@Query(nativeQuery = true, value = "update details d set d.paye = 1\n"
				+ "where d.namea like concat(:namea,'%') and d.date1 between :date1 and :date2 and d.date2 between :date1 and :date2")
		public void paiementParMoisHist(@Param("namea") String namea, @Param("date1") Date date1,
				@Param("date2") Date date2);

		
		@Modifying
		@Transactional
		@Query(nativeQuery = true, value = "update details d set d.paye = 0\n"
				+ "where d.namea  like concat(:namea,'%') and d.date1=:date1 and d.date2=:date2")
		public void compenseParMois(@Param("namea") String namea, @Param("date1") Date date1, @Param("date2") Date date2);
		
		
		
		@Query(nativeQuery = true, value = "select nom ,sum(total) as total ,sum(balance_paye) as balance_paye , sum(balance_n_paye) as balance_n_paye\n"
				+ "from (\n"
				+ "    select nom , 0 as total,0 as balance_paye ,round(sum(part_artiste),3) as balance_n_paye  from (\n"
				+ "			select       (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
				+ "			when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
				+ "			when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
				+ "			when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
				+ "			when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
				+ "			else namea end)as nom,namea, \n"
				+ "			ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
				+ "			from details  where  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =0 \n"
				+ "			)xx group by  nom\n"
				+ "union ALL\n"
				+ "select nom , 0 as total,round(sum(part_artiste),3) as balance_paye ,0 as balance_n_paye  from (\n"
				+ "			select       (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
				+ "			when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
				+ "			when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
				+ "			when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
				+ "			when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
				+ "			else namea end)as nom,namea, \n"
				+ "			ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
				+ "			from details  where  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =1\n"
				+ "			)xx group by  nom\n"
				+ "            union ALL\n"
				+ "select nom ,round(sum(part_artiste),3) as total,0 as balance_paye ,0 as balance_n_paye  from (\n"
				+ "			select       (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
				+ "			when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
				+ "			when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
				+ "			when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
				+ "			when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
				+ "			else namea end)as nom,namea, \n"
				+ "			ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
				+ "			from details  where  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
				+ "			)xx group by  nom\n"
				+ "    ) balance group by nom ")
		List<Map<String,Object>> ChartsPartsRevenu(@Param("id") Integer id); 
		
		
		
		
		
		
		
		
		
		
		@Query(nativeQuery = true, value = "select  namea, date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
				+ "round(sum(part_artiste),3) as part_artiste \n"
				+ "from details where  namea  in ((select  n_artistique FROM artiste u where u.content_provider_id=:id),'%')\n"
				+ "group by   date1,date2")	
		List<Map<String, Object>> statDateLabelByIdCp(@Param("id") Integer id);
		
		/*@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
				+ "from (\n"
				+ " select date1,date2, content,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
				+ " (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom,\n"
				+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,\n"
				+ "(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
				+ "from details  where     namea  LIKE CONCAT((select n_artistique FROM artiste u where u.content_provider_id=:id),'%')  group by  content,namea \n"
				+ "UNION ALL\n"
				+ " select  ch.nom as content, 0 as date1,0 as date2, 0 as ttc, 0 as quantite,0 as part_smart,\n"
				+ "0 as tax_telecom, 0 as part_TTC,\n"
				+ "0 as htva, 0 as part_artiste, u.n_artistique as namea\n"
				+ "from chanson ch \n"
				+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
				+ " where u.id =:id )xx\n"
				+ "group by  content,namea ")
		List<Map<String, Object>> TotStatChansonCpById(@Param("id") Integer id);*/
		
			
			/*@Query(nativeQuery = true, value = "select date1,date2, case when category  is null then 'VIDE' else category end as category , case when sub_category  is null then 'VIDE' else sub_category end as sub_category,  round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom, \n"
					+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste \n"
					+ "from details  where namea  LIKE CONCAT((select n_artistique FROM artiste u where u.content_provider_id=:id),'%')\n"
					+ "group by  category")
			List<Object[]> TotStatCategoryByIdCp(@Param("id") Integer id);*/
		
		
			
			@Query("SELECT dets.namea, dets.date1,dets.date2, round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where art.contentProvider.id = ?1 group by date1,date2")
		    List<Object[]> findAllProduitTypes(@Param("id") Integer id);
		    
		    @Query("SELECT dets.namea, dets.date1,dets.date2, round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets   join artiste art on art.nArtistique=dets.namea  where  dets.file = 'orange'  and art.contentProvider.id = ?1 group by namea, date1,date2")
		    List<Object[]> findAllProduitTypesOrange(@Param("id") Integer id);
		    
		    @Query("SELECT dets.namea, dets.date1,dets.date2, round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets   join artiste art on art.nArtistique=dets.namea  where  dets.file = 'TT'  and art.contentProvider.id = ?1 group by namea, date1,date2")
		    List<Object[]> findAllProduitTypesTT(@Param("id") Integer id);
		    
		    @Query("SELECT dets.namea, dets.date1,dets.date2, round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets   join artiste art on art.nArtistique=dets.namea  where  dets.file = 'Deezer'  and art.contentProvider.id = ?1 group  by  namea,date1,date2")
		    List<Object[]> findAllProduitTypesDeezer(@Param("id") Integer id);
		    @Query("SELECT dets.namea, dets.date1,dets.date2, round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets   join artiste art on art.nArtistique=dets.namea  where  dets.file = 'Believe'  and art.contentProvider.id = ?1 group by namea, date1,date2")
		    List<Object[]> findAllProduitTypesBeleieve(@Param("id") Integer id);
		    
		  /*  @Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
					+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
					+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
					+ "from (\n"
					+ " select date1,date2, content,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
					+ " (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom,\n"
					+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,\n"
					+ "(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
					+ "from details  where     namea  LIKE CONCAT((select n_artistique FROM artiste u where u.content_provider_id=:id),'%')  group by  content,namea \n"
					+ "UNION ALL\n"
					+ " select  ch.nom as content, 0 as date1,0 as date2, 0 as ttc, 0 as quantite,0 as part_smart,\n"
					+ "0 as tax_telecom, 0 as part_TTC,\n"
					+ "0 as htva, 0 as part_artiste, u.n_artistique as namea\n"
					+ "from chanson ch \n"
					+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
					+ " where u.id =:id )xx\n"
					+ "group by  content,namea ")*/
		    
		    @Query("SELECT  dets.date1,dets.date2,dets.namea, dets.content ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where art.contentProvider.id = ?1 group by content")
			List<Object[]> TotStatChansonCpById(@Param("id") Integer id);
		    
			@Query("SELECT  dets.date1,dets.date2,dets.namea, dets.content ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'Orange'  and art.contentProvider.id = ?1 group by content")
			List<Object[]> TotStatChansonCpByIdOrange(@Param("id") Integer id);
			
			
			@Query("SELECT  dets.date1,dets.date2,dets.namea, dets.content ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'TT'  and art.contentProvider.id = ?1 group by content")
			List<Object[]> TotStatChansonCpByIdTT(@Param("id") Integer id);
			
			@Query("SELECT  dets.date1,dets.date2,dets.namea, dets.content ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'Deezer'  and art.contentProvider.id = ?1 group by content")
			List<Object[]> TotStatChansonCpByIdDeezer(@Param("id") Integer id);
			
			@Query("SELECT  dets.date1,dets.date2,dets.namea, dets.content ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'Believe'  and art.contentProvider.id = ?1 group by content")
			List<Object[]> TotStatChansonCpByIdBeleiev(@Param("id") Integer id);
		    
			/*@Query(nativeQuery = true, value = "select date1,date2, case when category  is null then 'VIDE' else category end as category , case when sub_category  is null then 'VIDE' else sub_category end as sub_category,  round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, (case when round(sum(tax_telecom),3) is null then 0 else round(sum(tax_telecom),3) end ) as tax_telecom, \n"
					+ "(case when round(sum(part_TTC),3) is null then 0 else  round(sum(part_TTC),3) end)  as part_TTC,(case when round(sum(`htva`),3) is null then 0 else round(sum(`htva`),3) end) as htva, round(sum(part_artiste),3) as part_artiste \n"
					+ "from details  where namea  LIKE CONCAT((select n_artistique FROM artiste u where u.content_provider_id=:id),'%')\n"
					+ "group by  category")*/
		    @Query("SELECT  dets.date1,dets.date2, dets.category,dets.subCategory ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where art.contentProvider.id = ?1 group by date1,date2")
			List<Object[]> TotStatCategoryByIdCp(@Param("id") Integer id);
			
			@Query("SELECT  dets.date1,dets.date2, dets.category,dets.subCategory ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'orange' and art.contentProvider.id = ?1 group by date1,date2")
			List<Object[]> TotStatCategoryByIdCpOrange(@Param("id") Integer id);
		    
			@Query("SELECT  dets.date1,dets.date2, dets.category,dets.subCategory ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'TT' and art.contentProvider.id = ?1 group by date1,date2")
			List<Object[]> TotStatCategoryByIdCpTT(@Param("id") Integer id);
			
			 @Query("SELECT  dets.date1,dets.date2, dets.pays,dets.subCategory ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'Deezer' and art.contentProvider.id = ?1 group by date1,date2")
				List<Object[]> TotStatCategoryByIdCpDeezer(@Param("id") Integer id);
				
				@Query("SELECT  dets.date1,dets.date2, dets.pays,dets.subCategory ,round(sum(dets.TTC),3) as TTC, sum(dets.quantite) as quantite ,round(sum(dets.part_smart),3) as part_smart ,round( sum(dets.part_artiste),3) as part_artiste  FROM details dets  join artiste art on art.nArtistique=dets.namea  where dets.file = 'Believe' and art.contentProvider.id = ?1 group by date1,date2")
				List<Object[]> TotStatCategoryByIdCpBelieve(@Param("id") Integer id);
		    @Query(nativeQuery = true, value = "select  namea, date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
					+ "round(sum(part_artiste),3) as part_artiste \n"
					+ "from details where  namea  in ((select  n_artistique FROM artiste u where u.content_provider_id=:id),'%')\n"
					+ "group by   namea,date1,date2")	
			List<Object[]> statDateLabelById(@Param("id") Integer id);
			
			
			 /*@Query("SELECT dets.namea, dets.date1,dets  FROM details    group by date1,date2")
			    List<Object[]> findAllNArtisutique(@Param("id") String  id);*/
			
			List<Details> findByNamea(String namea);
}
