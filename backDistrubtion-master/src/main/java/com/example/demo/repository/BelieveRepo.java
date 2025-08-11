package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Interface.BelieveExcel;
import com.example.demo.entities.Believe;

public interface BelieveRepo  extends JpaRepository<Believe, Integer>{

	
	

	 @Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "   \n"
				+ "  round(sum(part_artiste),3) as part_artiste,cdate\n"
				+ "from details  where file ='Believe' group by date1,date2,cdate order by round((sum(ttc)),3) desc")
		List<Object[]> statDate();
	/*
	 * @Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "   \n"
			+ "  round(sum(part_artiste),3) as part_artiste,cdate\n"
			+ "from details  where file ='Believe' group by date1,date2,cdate")
	List<Object[]> statDate();
	*/
	
	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "   \n"
			+ "  round(sum(part_artiste),3) as part_artiste,cdate\n"
			+ "from details  where file ='Believe'   group by date1,date2,cdate order by round((sum(ttc)),3) desc")
	List<BelieveExcel> statDateBelieveExcel();
	
	
	
	@Query(nativeQuery = true, value = "select date1,date2, nom , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur\n"
			+ "from (\n"
			+ "select   (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "date1 as  date1 , date2 as date2, ttc  as ttc,ttc_eur  as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
			+ "from details  where file ='Believe'    \n"
			+ ")xx group by nom order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteBeelive();
	
	
	@Query(nativeQuery = true, value = "select namea as namea , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur\n"
			+ "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "ttc  as ttc,ttc_eur  as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
			+ "from details  where file ='Believe' \n"
			+ ")xx group by nom order by round((sum(ttc)),3) desc")
	List<BelieveExcel> statArtisteBeeliveExcel();
	
	
	
	
	@Query(nativeQuery = true, value = "select  date1,date2, content,namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select date1,date2, content,namea,   sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(part_artiste),3) as part_artiste\n"
			+ "from details dt where file ='Believe'    group by  content ,namea \n"
			+ "UNION ALL\n"
			+ "select  ch.nom as content, u.n_artistique as namea,0 as date1,0 as date2 ,0 quantite,0 as ttc_eur, 0 as ttc, 0 as part_smart,\n"
			+ "0 as part_artiste\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ ")xx\n"
			+ "group by  content,namea order by round((sum(ttc)),3) desc \n")
		List<Object[]> statChansonBelieve();
		
		
		@Query(nativeQuery = true, value = "select  content as content,namea as namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(part_artiste),3) as part_artiste from (\n"
				+ "select  content,namea,   sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(part_artiste),3) as part_artiste\n"
				+ "from details  where file ='Believe'    group by  content ,namea \n"
				+ "UNION ALL\n"
				+ "select  ch.nom as content, u.n_artistique as namea, 0 quantite,0 as ttc_eur, 0 as ttc, 0 as part_smart,\n"
				+ "0 as part_artiste\n"
				+ "from chanson ch \n"
				+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
				+ ")xx\n"
				+ "group by  content,namea order by round((sum(ttc)),3) desc \n")
			List<BelieveExcel> statChansonBelieveExcel();
		
		
		@Query(nativeQuery = true, value = "\r\n"
				+ " select  plateforme, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "   \n"
				+ "  round(sum(part_artiste),3) as part_artiste\n"
				+ "from details  where file ='Believe'   group by  plateforme order by round((sum(ttc)),3) desc")
		List<Object[]> statPlateformeBelieve();
		
		@Query(nativeQuery = true, value = "\r\n"
				+ " select  plateforme, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "   \n"
				+ "  round(sum(part_artiste),3) as part_artiste\n"
				+ "from details  where file ='Believe'   group by  plateforme order by round((sum(ttc)),3) desc")
		List<BelieveExcel> statPlateformeBelieveExcel();
		
		
		
		
		
		
		
		@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details  where file ='Believe'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
		List<Object[]> statDateById(@Param("id") Integer id);
		
		@Query(nativeQuery = true, value = "select   plateforme,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details  where file ='Believe'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
		List<Object[]> statDateByIdPlatforme(@Param("id") Integer id);
		
		
		@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,  \n"
				+ "round(sum(part_artiste),3) as part_artiste \n"
				+ "from details  where file ='Believe'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%')\n"
				+ "group by   date1,date2 order by round((sum(ttc)),3) desc")	
		List<Object[]> statDateLabelById(@Param("id") Integer id);
		
		@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details  where file ='Believe'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%')\n"
				+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
		List<Object[]> statDateByIdLabel(@Param("id") Integer id);
		
		
		@Query(nativeQuery = true, value = "select   date1,date2,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details  where file ='Believe'  and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
		List<BelieveExcel> statDateByIdExcel(@Param("id") Integer id);
		

		@Query(nativeQuery = true, value = "select date1,date2, content,namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select date1,date2, content,namea,   sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(part_artiste),3) as part_artiste\n"
			+ "from details dt where file ='Believe'   and    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  content ,namea \n"
			
			+ "UNION ALL\n"
			+ "select  ch.nom as content, u.n_artistique as namea,0 as date1, 0 as date2, 0 quantite,0 as ttc_eur, 0 as ttc, 0 as part_smart,\n"
			+ "0 as part_artiste\n"
			+ "from chanson ch \n"
			+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "where u.id =:id)xx\n"
			+ "group by  content,namea order by round((sum(ttc)),3) desc \n")
		List<Object[]> statChansonById(@Param("id") Integer id);
		
		
		
		@Query(nativeQuery = true, value = "select date1,date2, content,namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(part_artiste),3) as part_artiste from (\n"
				+ "select date1,date2, content,namea,   sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(part_artiste),3) as part_artiste\n"
				+ "from believe dt  where    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%') group by  content ,namea \n"
				
				+ "UNION ALL\n"
				+ "select  ch.nom as content, u.n_artistique as namea,0 as date1, 0 as date2, 0 quantite,0 as ttc_eur, 0 as ttc, 0 as part_smart,\n"
				+ "0 as part_artiste\n"
				+ "from chanson ch \n"
				+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
				+ "where u.id =:id)xx\n"
				+ "group by  content,namea  order by round((sum(ttc)),3) desc\n")
			List<Object[]> statChansonByIdLabel(@Param("id") Integer id);
		
		@Query(nativeQuery = true, value = "select date1,date2, nom , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur\n"
				+ "from (\n"
				+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
				+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
				+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
				+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
				+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
				+ "else namea end)as nom,namea,  \n"
				+ " date1 as date1, date2 as date2,ttc  as ttc,ttc_eur  as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
				+ "from details  where file ='Believe' and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  content ,namea   \n"
				+ ")xx group by nom order by round((sum(ttc)),3) desc")
		
		
		List<Object[]> statArtisteById(@Param("id") Integer id);
		
		
		
		
		@Query(nativeQuery = true, value = "select date1,date2, nom , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste, round((sum(`ttc_eur`)),3) as ttc_eur\n"
				+ "from (\n"
				+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
				+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
				+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
				+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
				+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
				+ "else namea end)as nom,namea,  \n"
				+ " date1 as date1, date2 as date2,ttc  as ttc,ttc_eur  as ttc_eur,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
				+ "from details  where file ='Believe' and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%') group by  content ,namea   \n"
				+ ")xx group by nom order by round((sum(ttc)),3) desc")
		
		
		List<Object[]> statArtisteByIdLabel(@Param("id") Integer id);
		
		@Query(nativeQuery = true, value = "select  content,namea, sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(part_artiste),3) as part_artiste from (\n"
				+ "select  content,namea,   sum(quantite) as quantite,round((sum(ttc_eur)),3) as ttc_eur,  round((sum(ttc)),3) as ttc, round(sum(part_smart),3) as part_smart,\n"
				+ "round(sum(part_artiste),3) as part_artiste\n"
				+ "from details dt  where file ='Believe'   and    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  content ,namea \n"
				
				+ "UNION ALL\n"
				+ "select  ch.nom as content, u.n_artistique as namea, 0 quantite,0 as ttc_eur, 0 as ttc, 0 as part_smart,\n"
				+ "0 as part_artiste\n"
				+ "from chanson ch \n"
				+ "left join artiste u on u.id = ch.idartiste_chasnon  \n"
				+ "where u.id =:id)xx\n"
				+ "group by  content,namea order by round((sum(ttc)),3) desc \n")
			List<BelieveExcel> statChansonByIdExcel(@Param("id") Integer id);
		
		

		@Query(nativeQuery = true, value = "select  plateforme,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details where file ='Believe' and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by plateforme order by round((sum(ttc)),3) desc")
		List<Object[]> statPlateformeById(@Param("id") Integer id);
		
		
		@Query(nativeQuery = true, value = "select  plateforme,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from believe where   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%')\n"
				+ "group by plateforme order by round((sum(ttc)),3) desc")
		List<Object[]> statPlateformeByIdLabel(@Param("id") Integer id);
		
		@Query(nativeQuery = true, value = "select  plateforme,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details where file ='Believe' and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by plateforme order by round((sum(ttc)),3) desc")
		List<BelieveExcel> statPlateformeByIdExcel(@Param("id") Integer id);
		
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
				+ "from details where file ='Believe'   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
		List<Object[]> statRevenuBeleive();
		
		
		
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
				+ "from details where file ='Believe'   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
		List<BelieveExcel> statRevenuBeleiveExcel();
		

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
				+ "from details where file ='Believe'  and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
				+ ")xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
		List<Object[]> HistRevenuBelieve(@Param("id") Integer id);
		
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
				+ "from details where file ='Believe'  and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
				+ ")xx group by date1,date2,nom,paye")
		List<BelieveExcel> HistRevenuBelieveExcel(@Param("id") Integer id);
		
		
		
		@Query(nativeQuery = true, value = "\r\n"
				+ " select date1,date2, pays, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "  round(sum(part_artiste),3) as part_artiste\n"
				+ "from details where file ='Believe' group by  pays order by round((sum(ttc)),3) desc")
		List<Object[]> statPaysBelieve();
		
		
		
		


		@Query(nativeQuery = true, value = "select  date1,date2, pays,   round((sum(ttc)),3) as ttc,   sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,   \n"
				+ "   round(sum(part_artiste),3) as part_artiste \n"
				+ "from details where file ='Believe'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
				+ "group by pays order by round((sum(ttc)),3) desc")
		List<Object[]> statPayBeleive(@Param("id") Integer id);
		
		
		
		@Modifying
		@Transactional
		@Query(value = "DELETE from details d where file ='Believe'  and d.date1=:date1 and d.date2=:date2 and d.file=:file")
		void deleteByDateFichier(@Param("date1") Date date1, @Param("date2") Date date2, @Param("file") String file);
		
		

		@Query(nativeQuery = true, value = "select (case when round((sum(part_artiste)),3) is null then 0 else  round((sum(part_artiste)),3) end)  as part_artiste\n"
	 			+ "from details where file ='Believe' dd\n"
				+ "where namea  LIKE CONCAT((select n_artistique FROM user u where u.id=:id),'%')\n"
				+ "and dd.date1 between :datedebut and :datefin and dd.date2 between :datedebut and :datefin and paye =0")
		List<Double> rapportStatTotalBelieveUsersById(@Param("id") Integer id, @Param("datedebut") Date datedebut,
				@Param("datefin") Date datefin);
		
		@Query(nativeQuery = true, value = "select  namea, round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
				+ "from details  where file ='Believe'  group by    namea ORDER BY  round(sum(ttc),3) desc limit 5")
		List<Object[]> topArtisteBelieve();
		
		@Query(nativeQuery = true, value = "select  namea,content, round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				+ "round(sum(ttc_eur),3) as ttc_eur, round(sum(part_TTC),3) as part_TTC,\n"
				+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
				+ "from details  where file ='Believe'  group by    namea ORDER BY  round(sum(ttc),3) desc limit 5")
		List<Object[]> topChansonBelieve();
		
		@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
				
			+ " round(sum(part_artiste),3) as part_artiste,round(sum(ttc_eur),3) as ttc_eur\n"
			+ "from details  where file ='Believe' group by date1,date2 desc limit 10")
	List<Object[]> topDateBelieve();
	
	@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details  where file ='Believe' group by content order by round((sum(part_artiste)),3) desc limit 5")
	List<Map<String,Object>> ChartsChansBelieve();
	
	
	
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
			+ "			from details  where file ='Believe'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =0 \n"
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
			+ "			from details  where file ='Believe'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =1\n"
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
			+ "			from details  where file ='Believe'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ "			)xx group by  nom\n"
			+ "    ) balance group by nom ")
	List<Map<String,Object>> ChartsPartsRevenuBeleive(@Param("id") Integer id); 
	
	
	
	
	
	
	
	@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name, round((sum(part_artiste)),3) as value from believe group by date1 order by date1 desc limit 5")
	List<Map<String,Object>> ChartsDateBelieve(); 
		
		
}
