package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Interface.TTExcel;
import com.example.demo.entities.TunisieTelecom;
import com.example.demo.intefrace.ArtisteGe;

public interface TunisieTelecomeRepo  extends JpaRepository<TunisieTelecom, Integer>{

	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details  where content_provider ='Smart' and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc" )
	List<Object[]> statDateTTById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_cp),3) as part_cp, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht \n"
			+ "from details where file ='TT' and  content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateCpById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(totalnetderedevance),3) as totalnetderedevance, round(sum(htva),3) as htva, round(sum(part_cp),3) as part_cp \n"
			+ "from details where  content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<TTExcel> statDateCpByIdExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where  content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,0 as part_artiste,0 as par_smat_ht ,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<Object[]> statCpChansonById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht,content_provider\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht,content_provider\n"
			+ "from details dt\n"
			+ " where namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,0 as part_artiste,0 as par_smat_ht,0 as content_provider ,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<Object[]> statCpChansonByIdLabel(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content_provider,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select date1,date2, content_provider,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as content_provider,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<TTExcel> statCpChansonByIdExcel(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as part_artiste,0 as par_smat_ht , u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<Object[]> statCpArtisteById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<TTExcel> statCpArtisteByIdExcel(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value ="select  sub_category,category,namea, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select sub_category ,category,namea, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp ,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where  content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  category,namea\n"
			+ "UNION ALL\n"
			+ " select  0 as sub_category,0 as category  , 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as part_artiste,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by  category,namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statTTCpCatById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select  date1,sub_category,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  date1,sub_category ,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp ,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where  content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select 0 as date1 ,0 as sub_category,0 as category  ,ch.nom as content, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statTTCpCatByIdExcel(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance\n"
			+ "from  details  where  content_provider ='Smart' and namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n order by round((sum(ttc)),3) desc")
	List<Object[]> statTTSmartMusicTotalById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea ,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1, date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='Smart' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,0 as part_artiste ,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as part_artiste,0 as par_smat_ht ,u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc ")
	List<Object[]> statTTSmartChansonById(@Param("id") Integer id);
	
	
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea ,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1, date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='Smart' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,0 as part_artiste ,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as part_artiste,0 as par_smat_ht ,u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<TTExcel> statTTSmartChansonByIdExcel(@Param("id") Integer id);
	
	/*@Query(nativeQuery = true, value = "select namea ,round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance\n" + "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"	
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom, part_cp, htva,  totalnetderedevance\n"
			+ "from details dt\n"
			+ " where content_provider ='Smart' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id)) group by  namea\n")*/
	@Query(nativeQuery = true, value = "select date1,date2, namea , round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht ,\n"
			+ " round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste, round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance from (\n"
			+ "select date1,date2, (select n_artistique FROM artiste u where u.id=:id)as namea,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht, \n"
			+ "round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste ,round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance \n"
			+ "from details  where content_provider ='Smart' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteTTById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select date1,date2, namea , round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht ,\n"
			+ " round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste, round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance from (\n"
			+ "select date1,date2, (select n_artistique FROM artiste u where u.id=:id)as namea,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht, \n"
			+ "round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste ,round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance \n"
			+ "from details  where content_provider ='Smart' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<TTExcel> statArtisteTTByIdExcel(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value ="select date1,date2, sub_category,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, sub_category ,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp ,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where  content_provider ='Smart' and    namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select 0 as date1,0 as date2, 0 as sub_category,0 as category  ,ch.nom as content, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as part_artiste,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statTTSmartCatById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select  sub_category,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select  sub_category ,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp ,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where  content_provider ='Smart' and    namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select 0 as sub_category,0 as category  ,ch.nom as content, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as part_artiste,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statTTSmartCatByIdExcel(@Param("id") Integer id);

	/*@Query(nativeQuery = true, value = "select  date1 as date1,date2 as date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance , round(sum(par_smat_ht),3) as par_smat_ht,cdate\n"
			+ "from details   group by date1,date2")*/
		@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
				+ " round(sum(`grossrevenu`),3) as grossrevenu,round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
				+ "from details  where file ='TT' and content_provider = 'Smart' group by date1,date2  order by round((sum(ttc)),3) desc")
		List<Object[]> statDateTT();
		
		
		//statChansonTTExcel
		@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
				+ " round(sum(`grossrevenu`),3) as grossrevenu,round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
				+ "from details  where file ='TT' and content_provider = 'Smart' group by date1,date2  order by round((sum(ttc)),3) desc")
		List<Object[]> statChansonTTExcel();
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_cp),3) as part_cp, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste ,round(sum(totalnetderedevance),3) as totalnetderedevance , round(sum(par_smat_ht),3) as par_smat_ht \n"
			+ "from details  where file ='TT' and content_provider ='Smart' and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateSmartById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(totalnetderedevance),3) as totalnetderedevance, round(sum(htva),3) as htva, round(sum(part_cp),3) as part_cp \n"
			+ "from details  where file ='TT' and content_provider ='Smart' and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<TTExcel> statDateSmartByIdExcel(@Param("id") Integer id);
	/*@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,cdate\n"
			+ "from details   group by date1,date2")*/
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where file ='TT'  group by date1,date2 order by round((sum(ttc)),3) desc")
	List<TTExcel> statDateTTExcel();
	@Query(nativeQuery = true, value = "SELECT date1, date2, namea, content,\n"
	        + "           ROUND(SUM(ttc), 3) as ttc,\n"
	        + "           SUM(quantite) as quantite,\n"
	        + "           ROUND(SUM(grossrevenu), 3) as grossrevenu,\n"
	        + "           ROUND(SUM(tax_telecom), 3) as tax_telecom,\n"
	        + "           ROUND(SUM(part_cp), 3) as part_cp,\n"
	        + "           ROUND(SUM(htva), 3) as htva,\n"
	        + "           ROUND(SUM(totalnetderedevance), 3) as totalnetderedevance,\n"
	        + "           ROUND(SUM(part_artiste), 3) as part_artiste,\n"
	        + "           ROUND(SUM(part_smart), 3) as part_smart\n"
	        + "    FROM (\n"
	        + "        SELECT\n"
	        + "            (CASE\n"
	        + "                WHEN namea LIKE '% Ft %' THEN SUBSTR(namea, 1, INSTR(namea, ' Ft ') - 1)\n"
	        + "                WHEN namea LIKE '% Et %' THEN SUBSTR(namea, 1, INSTR(namea, ' Et ') - 1)\n"
	        + "                WHEN namea LIKE '% Feat %' THEN SUBSTR(namea, 1, INSTR(namea, ' Feat ') - 1)\n"
	        + "                WHEN namea LIKE '% Ft.%' THEN SUBSTR(namea, 1, INSTR(namea, ' Ft.') - 1)\n"
	        + "                WHEN namea LIKE '% Feat.%' THEN SUBSTR(namea, 1, INSTR(namea, ' Feat.') - 1)\n"
	        + "                WHEN namea LIKE '% , %' THEN SUBSTR(namea, 1, INSTR(namea, ' , ') - 1)\n"
	        + "                WHEN namea LIKE '%, %' THEN SUBSTR(namea, 1, INSTR(namea, ', ') - 1)\n"
	        + "                ELSE namea\n"
	        + "            END) as nom,\n"
	        + "            namea, content, date1, date2, ttc, quantite, grossrevenu, tax_telecom,\n"
	        + "            part_cp, htva, totalnetderedevance, part_artiste, part_smart\n"
	        + "        FROM details\n"
	        + "        WHERE file = 'TT' AND content_provider = 'Smart'\n"
	        + "    ) AS xx\n"
	        + "    GROUP BY namea, content, date1, date2\n"
	        + "    ORDER BY ROUND(SUM(ttc), 3) DESC")
	List<Object[]> statArtisteTT();
	
	
	/*@Query(nativeQuery = true, value = "SELECT date1, date2, namea, content, ROUND(SUM(ttc), 3) as ttc, SUM(quantite) as quantite, ROUND(SUM(grossrevenu), 3) as grossrevenu, ROUND(SUM(tax_telecom), 3) as tax_telecom, ROUND(SUM(part_cp), 3) as part_cp, ROUND(SUM(htva), 3) as htva, ROUND(SUM(totalnetderedevance), 3) as totalnetderedevance, ROUND(SUM(part_artiste), 3) as part_artiste, ROUND(SUM(part_smart), 3) as part_smart\n" +
		    "FROM (\n" +
		    "    SELECT\n" +
		    "        (CASE\n" +
		    "            WHEN namea LIKE \"% Ft %\" THEN SUBSTR(namea, 1, INSTR(namea, \" Ft \"))\n" +
		    "            WHEN namea LIKE '% Et %' THEN SUBSTR(namea, 1, INSTR(namea, ' Et '))\n" +
		    "            WHEN namea LIKE '% Feat %' THEN SUBSTR(namea, 1, INSTR(namea, ' Feat '))\n" +
		    "            WHEN namea LIKE '% Ft.%' THEN SUBSTR(namea, 1, INSTR(namea, ' Ft.'))\n" +
		    "            WHEN namea LIKE '% Feat.%' THEN SUBSTR(namea, 1, INSTR(namea, ' Feat.'))\n" +
		    "            WHEN namea LIKE '% , %' THEN SUBSTR(namea, 1, INSTR(namea, ' , ')-1)\n" +
		    "            WHEN namea LIKE '%, %' THEN SUBSTR(namea, 1, INSTR(namea, ', ')-1)\n" +
		    "            ELSE namea\n" +
		    "        END) as nom,\n" +
		    "        namea,\n" +
		    "        content,\n" +
		    "        date1 as date1,\n" +
		    "        date2 as date2,\n" +
		    "        ttc as ttc,\n" +
		    "        quantite,\n" +
		    "        grossrevenu,\n" +
		    "        tax_telecom,\n" +
		    "        part_cp,\n" +
		    "        htva,\n" +
		    "        totalnetderedevance,\n" +
		    "        part_artiste as part_artiste,\n" +
		    "        part_smart as part_smart\n" +
		    "    FROM details\n" +
		    "    WHERE file = 'TT' AND content_provider = 'Smart'\n" +
		    ") AS xx\n" +
		    "GROUP BY namea, content\n" +
		    "ORDER BY ROUND(SUM(ttc), 3) DESC")
	
	List<Object[]> statArtisteTT();*/
	
	@Query(nativeQuery = true, value = "select date1,date2,namea ,round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n" + "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "date1 as date1,date2 as date2 ,ttc  as ttc,   quantite,  part_smart,  tax_telecom, part_cp, htva,  totalnetderedevance,part_artiste as part_artiste ,par_smat_ht as par_smat_ht\n"
			+ "from details  where file ='TT'   \n" + ")xx group by namea order by round((sum(ttc)),3) desc")
	List<TTExcel> statArtisteTTExcel();
	
	
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(grossrevenu),3) as grossrevenu, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(grossrevenu),3) as grossrevenu, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt where file ='TT' and content_provider = 'Smart' \n"
			+ "  group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as date1,0 as date2, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,0 as totalnetderedevance,0 as part_artiste,0 as par_smat_ht ,\n"
			+ "0 as grossrevenu, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by date1,content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonTT();
	
	
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2, category,sub_category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where file ='TT' and content_provider = 'Smart'  group by category order by round((sum(ttc)),3) desc ")
	List<Object[]> statcategoryTT();
	
	
	@Query(nativeQuery = true, value = "select  category,sub_category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where file ='TT'   group by category order by round((sum(ttc)),3) desc")
	List<TTExcel> statcategoryTTExcel();
	
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht,round(sum(part_artiste),3) as part_artiste\n"
			+ "from details  where  content_provider ='Qanawat' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<Object[]> statquanwatTotal();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where  content_provider ='Qanawat' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<TTExcel> statquanwatTotalExcel();
	
	@Query(value = "SELECT " +
	        "date1 AS date1, " +
	        "date2 AS date2, " +
	        "namea, " +
	        "ROUND(SUM(IFNULL(ttc, 0)), 3) AS ttc, " +
	        "SUM(IFNULL(quantite, 0)) AS quantite, " +
	        "ROUND(SUM(IFNULL(part_smart, 0)), 3) AS part_smart, " +
	        "ROUND(SUM(IFNULL(tax_telecom, 0)), 3) AS tax_telecom, " +
	        "ROUND(SUM(IFNULL(part_cp, 0)), 3) AS part_cp, " +
	        "ROUND(SUM(IFNULL(htva, 0)), 3) AS htva, " +
	        "ROUND(SUM(IFNULL(totalnetderedevance, 0)), 3) AS totalnetderedevance, " +
	        "ROUND(SUM(IFNULL(par_smat_ht, 0)), 3) AS par_smat_ht " +
	        "FROM details " +
	        "WHERE content_provider = 'Qanawat' " +
	        "GROUP BY namea " +
	        "ORDER BY ROUND(SUM(IFNULL(ttc, 0)), 3) DESC",
	       nativeQuery = true)
	List<Object[]> statArtisteQuanwat();
	/*@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where  content_provider ='Qanawat' group by namea order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statArtisteQuanwat();*/
	@Query(nativeQuery = true, value = "select   namea as namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where    content_provider ='Qanawat' group by namea order by round((sum(ttc)),3) desc")
	
	/*	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from  details  where content_provider ='Qanawat' group by namea ")*/
	
	List<TTExcel> statArtisteQuanwatExcel();
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht,round(sum(part_artiste),3) as part_artiste\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht,round(sum(part_artiste),3) as part_artiste\n"
			+ "from details  dt\n"
			+ " where content_provider ='Qanawat' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, 0 as par_smat_ht,0 as part_artiste,  u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonQuanwat();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details  dt\n"
			+ " where content_provider ='Qanawat' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<TTExcel> statChansonQuanwatExcel();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='Qanawat' group by sub_category order by round((sum(ttc)),3) desc ")
	List<Object[]> statSouCategoryQuanwat();
	
	@Query(nativeQuery = true, value = "select category as category,sub_category as sub_category,namea as namea ,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where  content_provider ='Qanawat' group by namea order by round((sum(ttc)),3) desc ")
	/*	@Query(nativeQuery = true, value = "select  sub_category as sub_category ,category as category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from  details  where content_provider ='Qanawat' group by namea ")*/
	List<TTExcel> statSouCategoryQuanwatExcel();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where  content_provider ='EKLECTIC' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statEKLECTIC();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='EKLECTIC' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<TTExcel> statEKLECTICExcel();
	
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where  content_provider ='EKLECTIC' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteEKLECTIC();
	
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='EKLECTIC' group by namea order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statArtisteEKLECTICExcel();
	
	/*@Query(nativeQuery = true, value ="select   content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='EKLECTIC' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content ,0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea  \n"
			+ " ")*/
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='EKLECTIC' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	
	
	List<Object[]> statChansonEkLECTIC();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='EKLECTIC' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	
	
	List<TTExcel> statChansonEkLECTICExcel();
	
	
	@Query(nativeQuery = true, value = "select date1,date2, sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='EKLECTIC' group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statSouCategoryEklectic();
	
	
	@Query(nativeQuery = true, value = "select sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='EKLECTIC' group by namea order by round((sum(ttc)),3) desc")
	List<TTExcel> statSouCategoryEklecticExcel();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where content_provider ='Smart' group by date1,date2 order by round((sum(ttc)),3) desc")
	
	List<Object[]> statSmart();
	
	
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='Smart' group by date1,date2 order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statSmartExcel();
	
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom,round(sum(par_smat_ht),3) as par_smat_ht , round(sum(part_cp),3) as part_cp,round(sum(part_artiste),3) as part_artiste,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Smart' group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteSmart();
	
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Smart' group by namea ")

	
	List<TTExcel> statArtisteSmartExcel();
	
	
	
	@Query(nativeQuery = true, value ="select  date1, date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='Smart' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2 ,0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom,0 as part_artiste,0 as par_smat_ht ,0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	
	List<Object[]> statChansonSmart();
	
	
	
	@Query(nativeQuery = true, value ="select  date1, date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='Smart' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2 ,0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom,0 as part_artiste,0 as par_smat_ht ,0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<TTExcel> statChansonSmartExcel();
	
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_artiste),3) as part_artiste,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='Smart' group by sub_category order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statSouCategorySmart();
	
	
	
	@Query(nativeQuery = true, value = "select  sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Smart' group by sub_category order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statSouCategorySmartExcel();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where content_provider ='ARPU Plus' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statARPUPlus();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where content_provider ='MusikBI' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statMusicBee();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where content_provider ='CHROME' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statChrome();
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where content_provider ='Digitalsound' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDigitalsound();
	
	
	//SpTunisi
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='SP_Tunisie' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<Object[]> statSpTunisi();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='CP_Tunisie' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statCpTunisi();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='Tunisie Telecom' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statTunisiTelecom();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='Tunisie Telecom' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<TTExcel> statTunisiTelecomExcel();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='CP_Tunisie' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<TTExcel> statCpTunisiExcel();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='SP_Tunisie' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<TTExcel> statSpTunisiExcel();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='ARPU Plus' group by date1,date2 order by round((sum(ttc)),3) desc")
	List<TTExcel> statARPUPlusExcel();
	
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='ARPU Plus' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteARPU();
	
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='MusikBI' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteMusicBee();
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='Digitalsound' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteDigitalsound();
	
	
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='CHROME' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteChrome();
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='SP_Tunisie' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteSP_Tunisie();
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='CP_Tunisie' group by namea order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statArtisteCpTunisie();
	
	
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Tunisie Telecom' group by namea order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statArtisteTunisieTelecom();
	
	
	
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Tunisie Telecom' group by namea order by round((sum(ttc)),3) desc ")
	
	List<TTExcel> statArtisteTunisieTelecomExce();
	
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='CP_Tunisie' group by namea order by round((sum(ttc)),3) desc ")
	
	List<TTExcel> statArtisteCpTunisieExcel();
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='SP_Tunisie' group by namea order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statArtisteSP_TunisieExcel();
	
	//SP_Tunisie
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='ARPU Plus' group by namea order by round((sum(ttc)),3) desc ")
	
	List<TTExcel> statArtisteARPUExcel();
	
	
	
	/*@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='ARPU Plus' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea  \n"
			+ " ")*/
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='ARPU Plus' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonARPUPlus();
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='MusikBI' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonMusicBee();
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='CHROME' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonChrome();
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='Digitalsound' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonDigitalSound();
	
	//SP_Tunisie
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='SP_Tunisie' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea  order by round((sum(ttc)),3) desc\n"
			+ " ")
	List<Object[]> statChansonSP_Tunisie();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='CP_Tunisie' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<Object[]> statChansonCpTunisie();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='Tunisie Telecom' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc  \n"
			+ " ")
	List<Object[]> statChansonTunisieTelecom();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='Tunisie Telecom' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<TTExcel> statChansonTunisieTelecomExcel();
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='CP_Tunisie' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<TTExcel> statChansonCpTunisieExcel();
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='SP_Tunisie' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<TTExcel> statChansonSP_TunisieExcel();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='ARPU Plus' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")
	List<TTExcel> statChansonSpExcel();
	
	@Query(nativeQuery = true, value = "select date1,date2, sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='ARPU Plus' group by sub_category order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statSouCategoryARPU();
	
	
	@Query(nativeQuery = true, value = "select  sub_category,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='SP_Tunisie' group by sub_category order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statSouCategorySP_Tunisie();
	
	
	

	@Query(nativeQuery = true, value = "select  sub_category,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='CP_Tunisie' group by sub_category order by round((sum(ttc)),3) desc ")
	
	List<Object[]> statSouCategoryCpTunisie();
	
	@Query(nativeQuery = true, value = "select  sub_category,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Tunisie Telecom' group by sub_category order by round((sum(ttc)),3) desc")
	
	List<Object[]> statSouCategoryTuniseTelecom();
	
	@Query(nativeQuery = true, value = "select  sub_category,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Tunisie Telecom' group by sub_category order by round((sum(ttc)),3) desc ")
	
	List<TTExcel> statSouCategoryTuniseTelecomExcel();
	
	@Query(nativeQuery = true, value = "select  sub_category,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='CP_Tunisie' group by sub_category order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statSouCategoryCpTunisieExcel();
	
	@Query(nativeQuery = true, value = "select  sub_category,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='SP_Tunisie' group by sub_category order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statSouCategorySP_TunisieExcel();
	//SP_Tunisie
	
	
	@Query(nativeQuery = true, value = "select  sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='ARPU Plus' group by sub_category order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statSouCategoryARPUExcel();
	
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details  where content_provider ='IVAS' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<Object[]> statIVAS();
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from details  where content_provider ='IVAS' group by date1,date2 order by round((sum(ttc)),3) desc ")
	List<TTExcel> statIVASExcel();
	
	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='IVAS' group by namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statArtisteIvasTT();
	
	
	@Query(nativeQuery = true, value = "select  namea,content_provider,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='IVAS' group by namea order by round((sum(ttc)),3) desc")
	
	List<TTExcel> statArtisteIvasTTExcel();
	
	/*@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='IVAS' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea  \n"
			+ " ")*/
	

	@Query(nativeQuery = true, value ="select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details dt\n"
			+ " where content_provider ='IVAS' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1 , 0 as date2, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance,0 as par_smat_ht, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")

	
	List<Object[]> statChansonIVAS();
	
	
	@Query(nativeQuery = true, value ="select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from (\n"
			+ "    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,namea\n"
			+ "from details dt\n"
			+ " where content_provider ='IVAS' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, 0 as ttc, 0 as part_cp,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n"
			+ " ")

	
	List<TTExcel> statChansonIVASExcel();
	
	
	@Query(nativeQuery = true, value = "select date1,date2, sub_category,category,namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from  details  where content_provider ='Ivas' group by sub_category order by round((sum(ttc)),3) desc ")
	List<Object[]> statSouCategoryIvas();
	
	
	@Query(nativeQuery = true, value = "select  sub_category,namea,category,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc\n"
			+ "from  details  where content_provider ='Ivas' group by sub_category order by round((sum(ttc)),3) desc")
	List<TTExcel> statSouCategoryIvasExcel();
	
	
	@Query(nativeQuery = true, value = "select nom ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(totalnetderedevance),3) as totalnetderedevance,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_cp),3) as part_cp, paye\n" + "from (\n"
			+ "select    date1,date2,  (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,   \n"
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  totalnetderedevance, htva,  part_cp,paye\n"
			+ "from details   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<Object[]> statRevenu();
	
	
	
	
	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(totalnetderedevance),3) as totalnetderedevance,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_cp),3) as part_cp, paye\n" + "from (\n"
			+ "select    date1,date2,  (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,   \n"
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  totalnetderedevance, htva,  part_cp,paye\n"
			+ "from details   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<TTExcel> statRevenuExcel();
	

	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(totalnetderedevance),3) as totalnetderedevance,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,paye,round(sum(par_smat_ht),3) as par_smat_ht\n" + "from (\n"
			+ "select   date1,date2,   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea, \n"
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  totalnetderedevance, htva,part_artiste , part_cp,paye,par_smat_ht\n"
			+ "from details  where namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<Object[]> HistRevenu(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(totalnetderedevance),3) as totalnetderedevance,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_cp),3) as part_cp,paye\n" + "from (\n"
			+ "select   date1,date2,   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea, \n"
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  totalnetderedevance, htva,  part_cp,paye\n"
			+ "from details  where namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<TTExcel> HistRevenuExcel(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(totalnetderedevance),3) as totalnetderedevance,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_cp),3) as part_cp,paye\n" + "from (\n"
			+ "select   date1,date2,   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea, \n"
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  totalnetderedevance, htva,  part_cp,paye\n"
			+ "from details  where   content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<Object[]> HistRevenuCp(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select content_provider,namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(totalnetderedevance),3) as totalnetderedevance,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_cp),3) as part_cp,paye\n" + "from (\n"
			+ "select   date1,date2,   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea, \n"
			+ "content_provider as content_provider,ttc  as ttc,   quantite,  part_smart,  tax_telecom,  totalnetderedevance, htva,  part_cp,paye\n"
			+ "from details  where   content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<TTExcel> HistRevenuCpExcel(@Param("id") Integer id);

	
	@Modifying
	@Transactional
	@Query(value = "DELETE from details d  where file = 'tt'  and d.date1=:date1 and d.date2=:date2 and d.file=:file")
	void deleteByDateFichier(@Param("date1") Date date1, @Param("date2") Date date2, @Param("file") String file);
	
	
	@Query(nativeQuery = true, value = "select (case when round((sum(part_artiste)),3) is null then 0 else  round((sum(part_artiste)),3) end)  as part_artiste\n"
 			+ "from details dd\n"
			+ "where namea  LIKE CONCAT((select n_artistique FROM user u where u.id=:id),'%')\n"
			+ "and dd.date1 between :datedebut and :datefin and dd.date2 between :datedebut and :datefin and paye =0")
	List<Double> rapportStatTotalBelieveUsersById(@Param("id") Integer id, @Param("datedebut") Date datedebut,
			@Param("datefin") Date datefin);
	
	
	
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_cp),3) as part_cp, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht,content_provider \n"
			+ "from details where    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateLabelById(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value = "select  namea, round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where content_provider ='Smart' group by    namea ORDER BY  round(sum(ttc),3) desc limit 5")
	List<Object[]> topArtisteSmart();
	
	
	
	


@Query(nativeQuery = true, value = "select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where content_provider ='Smart' group by  content order by  round(sum(ttc),3) desc limit 10")
	List<Object[]> topChansonSmart();
	
	
	
@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
		+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
		+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where content_provider ='Smart' group by date1,date2 desc limit 10")
	List<Object[]> topDateSmart();
	
	
	
	
	@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details group by content order by round((sum(part_artiste)),3) desc limit 5")
	List<Map<String,Object>> ChartsChansTT();
	
	
	
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
			+ "			from details where content_provider ='Smart'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =0 \n"
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
			+ "			from details  where content_provider ='Smart'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =1\n"
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
			+ "			from details where content_provider ='Smart'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ "			)xx group by  nom\n"
			+ "    ) balance group by nom ")
	List<Map<String,Object>> ChartsPartsRevenuTT(@Param("id") Integer id); 
	
	
	
	@Query(nativeQuery = true, value = "select  (case when (select pays from pays where abreviation =ds.pays) is null then ds.pays else \n"
			+ "(select pays from pays where abreviation =ds.pays)  end)  as value, \n"
			+ "round((sum(part_artiste)),3) as name from details ds where content_provider ='Smart' group by pays order by round((sum(part_artiste)),3) desc limit 6")
	List<Map<String,Object>> ChartsPaysTT(); 
	
	
	
	
	@Query(nativeQuery = true, value = "select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_cp),3) as part_cp, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht \n"
			+ "from details  where content_provider ='Smart'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  content order by round((sum(ttc)),3) desc limit 10")
	List<Object[]> statChansonUsersByIdTT(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name, round((sum(part_artiste)),3) as value from details  where content_provider ='Smart' group by date1 order by date1 desc limit 5")
	List<Map<String,Object>> ChartsDateTT(); 
	
	
	

	@Query(nativeQuery = true, value = "\r\n"
			+ " select  plateforme, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where file = 'TT'  and content_provider = 'Smart'  group by  plateforme order by round((sum(ttc)),3) desc")
	List<Object[]> statPlateformeTT();
	

	
	@Query(nativeQuery = true, value = "\r\n"
			+ " select  plateforme, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where file = 'TT'  and    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') "
			+ "group by  plateforme order by round((sum(ttc)),3) desc")
	List<Object[]> statPlateformeTTById(@Param("id") Integer id);
	/*@Query(nativeQuery = true, value = "select  plateforme, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'TT' and    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by plateforme order by round((sum(ttc)),3) desc")
	List<Object[]> statPlateformeTTById(@Param("id") Integer id);*/
	
	
	
	
}
