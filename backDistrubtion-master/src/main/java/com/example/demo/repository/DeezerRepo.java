package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Interface.DeezerExcel;
import com.example.demo.entities.Deezer;


public interface DeezerRepo  extends JpaRepository<Deezer, Integer>{

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
	
	
	
	
	
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "  round(sum(part_artiste),3) as part_artiste,cdate\n"
			+ "from details where file = 'Deezer' group by date1,date2,cdate order by round((sum(ttc)),3) desc")
	List<Object[]> statDate();
	
	
	
	@Query(nativeQuery = true, value = "select  plateforme,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "  round(sum(part_artiste),3) as part_artiste\n"
			+ "from details where file = 'Deezer'  order by round((sum(ttc)),3) desc")
	List<Object[]> statPLatforme();
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "  round(sum(part_artiste),3) as part_artiste,cdate\n"
			+ "from details where file = 'Deezer'   group by date1,date2,cdate order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statDateDeezerExcel();
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2, nom ,sum(quantite) as quantite, round((sum(`ttc_eur`)),3) as ttc_eur, round((sum(`ttc`)),3) as ttc,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(part_artiste),3) as part_artiste\n"
			+ "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "date1 as date1,date2 as date2, ttc  as ttc,  ttc_eur, quantite,  part_smart, part_artiste\n"
			+ "from details where file = 'Deezer'    \n"
			+ ")xx group by nom order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteDeezer();
	
	

	@Query(nativeQuery = true, value = "select namea as namea ,sum(quantite) as quantite, round((sum(`ttc_eur`)),3) as ttc_eur, round((sum(`ttc`)),3) as ttc,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(part_artiste),3) as part_artiste\n"
			+ "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "ttc  as ttc,  ttc_eur, quantite,  part_smart, part_artiste\n"
			+ "from details where file = 'Deezer'   \n"
			+ ")xx group by nom order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statArtisteDeezerExcel();
	
	
	
	
	
	@Query(nativeQuery = true, value = "select  namea , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur\n"
			+ "from (\n"
			+ "select     namea,  \n"
			+ "ttc  as ttc,  ttc_eur, quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
			+ "from details where file = 'Deezer'    \n"
			+ ")xx group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statFournisseurDeezer();
	
	
	@Query(nativeQuery = true, value = "select  namea , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur\n"
			+ "from (\n"
			+ "select     namea,  \n"
			+ "ttc  as ttc,  ttc_eur, quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
			+ "from details where file = 'Deezer'   \n"
			+ ")xx group by namea order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statFournisseurDeezerExcel();
	
	@Query(nativeQuery = true, value ="select date1,date2, content,namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur, round((sum(ttc)),3) as ttc,round(sum(part_smart),3) as part_smart,\n"
			+ " round(sum(part_artiste),3) as part_artiste\n"
			+ "from (\n"
			+ "select  date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(part_artiste),3) as part_artiste,namea, round((sum(ttc_eur)),3) as ttc_eur\n"
			+ "from details dt where file ='Deezer'\n"
			+ "group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, u.n_artistique as namea,0 as date1,0 as date2, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ " 0 as part_artiste, 0 as ttc_eur\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon   \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc  \n" 
			+ " ")
	List<Object[]> statChansonDeezer();
	
	
	@Query(nativeQuery = true, value ="select  content,namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur, round((sum(ttc)),3) as ttc,round(sum(part_smart),3) as part_smart,\n"
			+ " round(sum(part_artiste),3) as part_artiste\n"
			+ "from (\n"
			+ "select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(part_artiste),3) as part_artiste,namea, round((sum(ttc_eur)),3) as ttc_eur\n"
			+ "from details dt\n"
			+ " where file ='Deezer' group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content, u.n_artistique as namea, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ " 0 as part_artiste, 0 as ttc_eur\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon   \n"
			+ "    )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc \n" 
			+ " ")
	List<DeezerExcel> statChansonDeezerExcel();
	
	
	
	@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc ,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
			+ "   round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select   plateforme,   round((sum(ttc)),3) as ttc ,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
			+ "   round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateByIdPlatforme(@Param("id") Integer id);
	@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
			+ "   round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Deezer'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateByIdLabel(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
			+ "   round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Deezer'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statDateByIdExcel(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select namea , sum(quantite) as quantite, round((sum(`ttc_eur`)),3) as ttc_eur , round((sum(ttc)),3) as tcc,round(sum(part_smart),3) as part_smart, \n"
			+ " round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select  (select n_artistique FROM artiste u where u.id=:id)as namea,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,  \n"
			+ "  round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur \n"
			+ "from details where file = 'Deezer'  and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and namef is null\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteDeezerById(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2, namea , sum(quantite) as quantite, round((sum(`ttc_eur`)),3) as ttc_eur , round((sum(ttc)),3) as tcc,round(sum(part_smart),3) as part_smart, \n"
			+ " round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select date1 as date1, date2 as date2,  (select n_artistique FROM artiste u where u.idlabel=:id)as namea,    round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,  \n"
			+ "  round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur \n"
			+ "from details where file = 'Deezer'  and    namea LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%') and namef is null\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteDeezerByIdLabel(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select namea , sum(quantite) as quantite, round((sum(`ttc_eur`)),3) as ttc_eur , round((sum(ttc)),3) as tcc,round(sum(part_smart),3) as part_smart, \n"
			+ " round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select  (select n_artistique FROM artiste u where u.id=:id)as namea,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,  \n"
			+ "  round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur \n"
			+ "from details where file = 'Deezer'  and    namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and namef is null\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statArtisteDeezerByIdExcel(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom (\n    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom details where file = 'Deezer'  dt\n and namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  date1,content\nUNION ALL\n select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n0 as ttc_eur, 0 as part_TTC,\n0 as htva, 0 as part_artiste, u.n_artistique as namea\nfrom chanson ch \nleft join artiste u on u.id = ch.idartiste_chasnon  \n  where u.id=:id  )xx\n      group by date1,content order by round((sum(ttc)),3) desc")

	List<Object[]> statChansonDeezerById(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from (\n"
			+ "    select date1,date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from details where file = 'Deezer' dt\n"
			+ " and namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as ttc_eur, 0 as part_TTC,\n"
			+ "0 as htva, 0 as part_artiste, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")

	List<DeezerExcel> statChansonDeezerByIdExcel(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content,namea, sum(quantite) as quantite, round((sum(ttc_eur)),3) as ttc_eur,round((sum(ttc)),3) as ttc,round(sum(part_smart),3) as part_smart, \n"
			+ "			  round(sum(part_artiste),3) as part_artiste \n"
			+ "			 from ( \n"
			+ "			 select date1,date2, content,namea, round((sum(ttc_eur)),3) as ttc_eur , round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart ,\n"
			+ "			 round(sum(part_artiste),3) as part_artiste\n"
			+ "			 from details dt where file = 'Deezer'  \n"
			+ "			  and   namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  content,namea \n"
			+ "			 UNION ALL \n"
			+ "			  select  ch.nom as content, u.n_artistique as namea,0 as date1,0 as date2 ,0 as ttc_eur, 0 as ttc, 0 as quantite,0 as part_smart, \n"
			+ "			 0 as part_artiste\n"
			+ "			 from chanson ch  \n"
			+ "			 left join artiste u on u.id = ch.idartiste_chasnon   \n"
			+ "			 where u.id=:id  )xx \n"
			+ "			 group by  content,namea  \n"
			+ "			 ORDER BY `ttc`  DESC")
	List<Object[]> statChansonById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value ="select date1,date2, content,namea, sum(quantite) as quantite, round((sum(ttc_eur)),3) as ttc_eur,round((sum(ttc)),3) as ttc,round(sum(part_smart),3) as part_smart, \n"
			+ "			  round(sum(part_artiste),3) as part_artiste \n"
			+ "			 from ( \n"
			+ "			 select date1,date2, content,namea, round((sum(ttc_eur)),3) as ttc_eur , round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart ,\n"
			+ "			 round(sum(part_artiste),3) as part_artiste\n"
			+ "			 from details where file = 'Deezer' dt \n"
			+ "			  and   namea LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%') group by  content,namea \n"
			+ "			 UNION ALL \n"
			+ "			  select  ch.nom as content, u.n_artistique as namea,0 as date1,0 as date2 ,0 as ttc_eur, 0 as ttc, 0 as quantite,0 as part_smart, \n"
			+ "			 0 as part_artiste\n"
			+ "			 from chanson ch  \n"
			+ "			 left join artiste u on u.id = ch.idartiste_chasnon   \n"
			+ "			 where u.id=:id  )xx \n"
			+ "			 group by  content,namea  \n"
			+ "			 ORDER BY `ttc`  DESC")
	List<Object[]> statChansonByIdLabel(@Param("id") Integer id);
	
	
	
	
	
	@Query(nativeQuery = true, value = "select nom ,date1,date2, round((sum(`ttc`)),3) as ttc,round((sum(`ttc_eur`)),3) as ttc_eur,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
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
			+ "ttc  as ttc, ttc_eur as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "from details where file = 'Deezer'   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<Object[]> statRevenuDeezer();
	
	
	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,round((sum(`ttc_eur`)),3) as ttc_eur,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
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
			+ "ttc  as ttc, ttc_eur as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "from details where file = 'Deezer'   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statRevenuDeezerExcel();
	
	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,round((sum(`ttc_eur`)),3) as ttc_eur,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,paye\n" + "from (\n"
			+ "select   date1,date2,   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea, \n"
			+ "ttc  as ttc, ttc_eur as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "from details where file = 'Deezer'  and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<Object[]> HistRevenuDeezer(@Param("id") Integer id);
	
	
	
	
	
	
	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,round((sum(`ttc_eur`)),3) as ttc_eur,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,paye\n" + "from (\n"
			+ "select   date1,date2,   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
		    + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea, \n"
			+ "ttc  as ttc, ttc_eur as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "from details where file = 'Deezer'  and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<DeezerExcel> HistRevenuDeezerExcel(@Param("id") Integer id);
	
	
	
	
	
	
	
	
	
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(htva),3) as htva, round(sum(part_cp),3) as part_cp \n"
			+ "from details where file = 'Deezer' and  content_provider    LIKE CONCAT((select nom_content  FROM content_provider  u where u.id=:id),'%')\n"
			+ "group by   date1,date2")
	List<Object[]> statDateFourisseurById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(htva),3) as htva, round(sum(part_cp),3) as part_cp \n"
			+ "from details where file = 'Deezer' and  content_provider    LIKE CONCAT((select nom_content  FROM content_provider  u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statDateFourisseurByIdExcel(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from details where file = 'Deezer' dt\n"
			+ " and content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as ttc_eur, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<Object[]> statCpChansonById(@Param("id") Integer id);
	
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from details where file = 'Deezer' dt\n"
			+ " and content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.nom_content=:nomcontent),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as ttc_eur, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.nom_content = ch.idartiste_chasnon  \n"
			+ "  where u.nom_content=:nomcontent  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<Object[]> statCpChansonByNom(@Param("nom") String nomcontent);
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from (\n"
			+ "    select date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from details where file = 'Deezer' dt\n"
			+ " and content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as ttc_eur, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by date1,content order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statCpChansonByIdExcel(@Param("id") Integer id);
	
	
	
	
	@Query(nativeQuery = true, value ="select  date1,date2,sub_category,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from (\n"
			+ "    select  date1,date2,sub_category ,category,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_cp),3) as part_cp ,\n"
			+ "round(sum(htva),3) as htva, round(sum(ttc_eur),3) as ttc_eur,namea\n"
			+ "from details where file = 'Deezer' dt\n"
			+ " and  content_provider  LIKE CONCAT((select nom_content FROM content_provider u where u.id=:id),'%') group by  content,namea\n"
			+ "UNION ALL\n"
			+ " select  0 as date1,0 as date2 ,0 as sub_category,0 as category  ,ch.nom as content, 0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_cp,\n"
			+ "0 as htva, 0 as totalnetderedevance, u.n_artistique as namea\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n"
			+ "      group by  content,namea order by round((sum(ttc)),3) desc")
	
	List<Object[]> statTTCpCatById(@Param("id") Integer id);
	
	
	@Query(nativeQuery = true, value = "\r\n"
			+ "  select  date1,date2, pays, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "  round(sum(part_artiste),3) as part_artiste\n"
			+ "from details where file = 'Deezer' group by  pays order by round((sum(ttc)),3) desc")
	List<Object[]> statPaysDeezer();
	
	@Query(nativeQuery = true, value = "\r\n"
			+ " select  pays, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "  round(sum(part_artiste),3) as part_artiste\n"
			+ "from details where file = 'Deezer' group by  pays order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statPaysDeezerExce();
	
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2,  pays,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
			+ "   round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by pays order by round((sum(ttc)),3) desc")
	List<Object[]> statPaysDeezer(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "select   pays,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
			+ "   round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM user u where u.id=:id),'%')\n"
			+ "group by pays order by round((sum(ttc)),3) desc")
	List<DeezerExcel> statPaysDeezerExcel(@Param("id") Integer id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE from details d where file = 'Deezer'  and d.date1=:date1 and d.date2=:date2 and d.file=:file")
	void deleteByDateFichier(@Param("date1") Date date1, @Param("date2") Date date2, @Param("file") String file);
	
	
	@Query(nativeQuery = true, value = "select (case when round((sum(part_artiste)),3) is null then 0 else  round((sum(part_artiste)),3) end)  as part_artiste\n"
 			+ "from details dd where file = 'Deezer'\n"
			+ "where namea  LIKE CONCAT((select n_artistique FROM user u where u.id=:id),'%')\n"
			+ "and dd.date1 between :datedebut and :datefin and dd.date2 between :datedebut and :datefin and paye =0")
	List<Double> rapportStatTotalBelieveUsersById(@Param("id") Integer id, @Param("datedebut") Date datedebut,
			@Param("datefin") Date datefin);
	
	
	
	
	@Query(nativeQuery = true, value = "select  namea, round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from details    group by    namea ORDER BY  round(sum(ttc),3) desc limit 5")
	List<Object[]> topArtisteDeezer();
	
	
	
	@Query(nativeQuery = true, value = "select  content,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			 + "  round(sum(part_artiste),3) as part_artiste\n"
			+ "from details group by  content order by  round(sum(ttc),3) desc limit 10")
	List<Object[]> topChansonDeezer();
	
	
	
@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			
			+ " round(sum(part_artiste),3) as part_artiste\n"
			+ "from details  desc limit 10")
	List<Object[]> topDateDeezer();
	/*
	 * 
	 */
	
	

@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details where file = 'Deezer' group by content order by round((sum(part_artiste)),3) desc limit 5")
	List<Map<String,Object>> ChartsChansDeezer();






@Query(nativeQuery = true, value = "select nom ,sum(total) as total ,sum(balance_paye) as balance_paye , sum(balance_n_paye) as balance_n_paye\n"
		+ "from (\n"
		+ "    select nom , 0 as total,0 as balance_paye ,round(sum(part_artiste),3) as balance_n_paye  from (\n"
		+ "			select       (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
		+ "			when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
		+ "			when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
		+ "			when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
		+ "			when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
		+ "			else namea end)as nom,namea, \n"
		+ "			part_smart_globale  as part_smart_globale,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
		+ "			from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =0 \n"
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
		+ "			from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =1\n"
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
		+ "			from details where file = 'Deezer'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
		+ "			)xx group by  nom\n"
		+ "    ) balance group by nom ")
List<Map<String,Object>> ChartsPartsRevenuDeezer(@Param("id") Integer id);


@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name, round((sum(part_artiste)),3) as value from details where file = 'Deezer' group by date1 order by date1 desc limit 5")
List<Map<String,Object>> ChartsDateDeezer(); 

	
}








