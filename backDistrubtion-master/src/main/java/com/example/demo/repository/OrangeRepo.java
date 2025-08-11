package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Interface.OrangeExcel;
import com.example.demo.entities.Orange;

public interface OrangeRepo extends JpaRepository<Orange, Integer> {

	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateById(@Param("id") Integer id);

	/*
	 * @Query(nativeQuery = true, value =
	 * "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
	 * +
	 * "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
	 * +
	 * "from orange  where  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id) and (),'%')\n"
	 * + "group by   date1,date2") List<Object[]> statDateByIdLabel(@Param("id")
	 * Integer id);
	 */

	/*
	 * @Query(nativeQuery = true, value =
	 * "select  namea, date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
	 * +
	 * "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
	 * +
	 * "from details where  namea  = CONCAT((select  n_artistique FROM artiste u where u.cp_id=:id ORDER BY n_artistique DESC LIMIT 1),'%')\n"
	 * + "group by   namea,date1,date2") List<Object[]>
	 * statDateLabelById(@Param("id") Integer id);
	 */

	@Query(nativeQuery = true, value = "select  namea, date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where  namea  = CONCAT((select  n_artistique FROM artiste u where u.artistes_id	=:id),'%')\n"
			+ "group by   namea,date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateLabelById(@Param("id") Integer id);

	/*
	 * @Query(nativeQuery = true, value =
	 * "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
	 * +
	 * "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
	 * +
	 * "from details where file = 'Orange' and  namea  LIKE CONCAT((select n_artistique FROM artiste) and (select content_provider_id IN content_provider_artistes u where u.content_provider_id=:id)  ,'%')\n"
	 * + "group by   date1,date2") List<Object[]> statDateLabelById(@Param("id")
	 * Integer id);
	 */

	@Query(nativeQuery = true, value = "select   date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by   date1,date2 order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statDateByIdExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select date1,date2, namea , round((sum(ttc)),3) as tcc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,\n"
			+ " round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select  (select n_artistique FROM artiste u where u.id=:id)as namea,date1 as date1,date2 as date2,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteOrangeById(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select date1,date2, namea,content , round((sum(ttc)),3) as tcc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,\n"
			+ " round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select  (select n_artistique FROM artiste u where u.id=:id)as namea,date1 as date1,date2 as date2,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%')\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc")
	List<Object[]> statArtisteOrangeByIdLabel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select date1, namea ,content, round((sum(ttc)),3) as tcc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom,\n"
			+ " round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste from (\n"
			+ "select  (select n_artistique FROM artiste u where u.id=:id)as namea,   round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and  namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  namea)xx group by namea order by round((sum(ttc)),3) desc ")
	List<OrangeExcel> statArtisteOrangeByIdExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n" + "from (\n"
			+ "    select date1, date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\n"
			+ "from details dt where file = 'Orange' \n"
			+ " and namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  date1,content\n"
			+ "UNION ALL\n"
			+ " select ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n"
			+ "0 as tax_telecom, 0 as part_TTC,\n" + "0 as htva, 0 as part_artiste, u.n_artistique as namea\n"
			+ "from chanson ch \n" + "left join artiste u on u.id = ch.idartiste_chasnon  \n"
			+ "  where u.id=:id  )xx\n" + "      group by date1,content order by round((sum(ttc)),3) desc")

	List<Object[]> statChansonOrangeById(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  date1,date2,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom (\n    select date1, date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom details where file = 'Orange' dt\n where namea LIKE CONCAT((select n_artistique FROM artiste u where u.idlabel=:id),'%') group by  date1,content\nUNION ALL\n select  ch.nom as content,0 as date1,0 as date2,0 as ttc, 0 as quantite,0 as part_smart,\n0 as tax_telecom, 0 as part_TTC,\n0 as htva, 0 as part_artiste, u.n_artistique as namea\nfrom chanson ch \nleft join artiste u on u.id = ch.idartiste_chasnon  \n  where u.id=:id  )xx\n      group by date1,content order by round((sum(ttc)),3) desc")

	List<Object[]> statChansonOrangeByIdLabel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  date1 as date1,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom (\n    select date1 as date1, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom details where file = 'Orange' dt\n where namea LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') group by  date1,content\nUNION ALL\n select  0 as date1,ch.nom as content,0 as ttc, 0 as quantite,0 as part_smart,\n0 as tax_telecom, 0 as part_TTC,\n0 as htva, 0 as part_artiste, u.n_artistique as namea\nfrom chanson ch \nleft join artiste u on u.id = ch.idartiste_chasnon  \n  where u.id=:id  )xx\n      group by date1,content order by round((sum(ttc)),3) desc")

	List<OrangeExcel> statChansonOrangeByIdExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  plateforme, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and    namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by plateforme order by round((sum(ttc)),3) desc")
	List<Object[]> statPlateformeOrangeById(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  plateforme,namea, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by plateforme order by round((sum(ttc)),3) desc")
	List<Object[]> statPlateformeOrangeByIdLabel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  plateforme, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and   namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by plateforme order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statPlateformeOrangeByIdExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select date1,date2, category, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  category order by round((sum(ttc)),3) desc")
	List<Object[]> statCategoryOrangeById(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  category, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%')\n"
			+ "group by  category order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statCategoryOrangeByIdExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select *  from details where file = 'Orange' and date1=:date1 and date2=:date2 and file=:file")
	List<Orange> getDetailsByDate1andDate2andFile(@Param("date1") Date date1, @Param("date2") Date date2,
			@Param("file") String file);

	@Query(value = "SELECT " + "  date1, " + "  date2, " + "  ROUND(SUM(ttc),3)        AS ttc, "
			+ "  SUM(quantite)             AS quantite, " + "  ROUND(SUM(part_smart),3)  AS part_smart, "
			+ "  ROUND(SUM(tax_telecom),3) AS tax_telecom, " + "  ROUND(SUM(part_TTC),3)    AS part_TTC, "
			+ "  ROUND(SUM(htva),3)        AS htva, " + "  ROUND(SUM(part_artiste),3)AS part_artiste " + "FROM details "
			+ "WHERE file = 'Orange' " + "GROUP BY date1, date2 " // <-- date2 ajouté ici
			+ "ORDER BY ttc DESC", nativeQuery = true)
	List<Object[]> statDate();

	@Query(nativeQuery = true, value = "select  date1 ,date2,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from  details where file = 'Orange'  group by date1 order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statDateOrangeExcel();

	/*@Query(nativeQuery = true, value = "SELECT * FROM artiste_stat_view")
	List<Object[]> statArtisteOrange();*/

	@Query(nativeQuery = true, value = "SELECT date1,date2 , namea,  content,\n"
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
	        + "        WHERE file = 'Orange'\n"
	        + "    ) AS xx\n"
	        + "    GROUP BY namea, content, date1, date2\n"
	        + "    ORDER BY ROUND(SUM(ttc), 3) DESC")
	List<Object[]> statArtisteOrange();
	/*@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom , round(sum(part_TTC),3) as part_TTC,round(sum(part_artiste),3) as part_artiste,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc , uniteprice\n"
			+ "from  details   where file = 'Orange' group by namea, date1 ,uniteprice order by round((sum(ttc)),3) desc")
	List<Object[]> statArtistOrangeUnti();*/
	
/*@Query(
			  value = "SELECT date1, MAX(date2) AS date2, namea, " +
			          "ROUND(SUM(ttc), 3) AS ttc, SUM(quantite) AS quantite, " +
			          "ROUND(SUM(part_smart), 3) AS part_smart, " +
			          "ROUND(SUM(tax_telecom), 3) AS tax_telecom, " +
			          "ROUND(SUM(part_TTC), 3) AS part_TTC, " +
			          "ROUND(SUM(part_artiste), 3) AS part_artiste, " +
			          "ROUND(SUM(htva), 3) AS htva, " +
			          "ROUND(SUM(totalnetderedevance), 3) AS totalnetderedevance, " +
			          "ROUND(SUM(part_TTC), 3) AS part_ttc, uniteprice " +
			          "FROM details WHERE file = 'Orange' " +
			          "GROUP BY namea, date1, uniteprice " +
			          "ORDER BY ttc DESC",
			  nativeQuery = true
			)
List<Object[]> statArtistOrangeUnti();*/

@Query(
		    value = "SELECT date1, MAX(date2) AS date2, namea, " +
		            "ROUND(SUM(ttc), 3) AS ttc, SUM(quantite) AS quantite, " +
		            "ROUND(SUM(part_smart), 3) AS part_smart, " +
		            "ROUND(SUM(tax_telecom), 3) AS tax_telecom, " +
		            "ROUND(SUM(part_TTC), 3) AS part_TTC, " +
		            "ROUND(SUM(part_artiste), 3) AS part_artiste, " +
		            "ROUND(SUM(htva), 3) AS htva, " +
		            "ROUND(SUM(totalnetderedevance), 3) AS totalnetderedevance, " +
		            "ROUND(SUM(part_TTC), 3) AS part_ttc, uniteprice " +
		            "FROM details " +
		            "WHERE file = 'Orange' AND (:namea IS NULL OR namea = :namea) " +
		            "GROUP BY namea, date1, uniteprice " +
		            "ORDER BY ttc DESC",
		    nativeQuery = true
		)
		List<Object[]> statArtistOrangeUnti(@Param("namea") String namea);




	@Query(nativeQuery = true, value = "select date1,date2, namea,round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom , round(sum(part_TTC),3) as part_TTC,round(sum(part_artiste),3) as part_artiste,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(part_ttc),3) as part_ttc , uniteprice\n"
			+ "from  details   where  content_provider ='Smart' group by namea, date1 ,uniteprice order by round((sum(ttc)),3) desc")
	List<Object[]> statArtistTTUnti();
	/*
	 * @Query(nativeQuery = true, value = "SELECT \n" + "    nom,\n" +
	 * "    date1,\n" + "    date2,\n" + "    content,\n" +
	 * "    ROUND(SUM(ttc), 3) AS ttc,\n" + "    SUM(quantite) AS quantite,\n" +
	 * "    ROUND(SUM(part_smart), 3) AS part_smart,\n" +
	 * "    ROUND(SUM(tax_telecom), 3) AS tax_telecom,\n" +
	 * "    ROUND(SUM(part_TTC), 3) AS part_TTC,\n" +
	 * "    ROUND(SUM(htva), 3) AS htva,\n" +
	 * "    ROUND(SUM(part_artiste), 3) AS part_artiste\n" + "FROM (\n" +
	 * "    SELECT \n" + "        CASE \n" +
	 * "            WHEN namea LIKE \"% Ft %\" THEN SUBSTR(namea, 1, INSTR(namea, \" Ft \")) \n"
	 * +
	 * "            WHEN namea LIKE '% Et %' THEN SUBSTR(namea, 1, INSTR(namea, ' Et '))  \n"
	 * +
	 * "            WHEN namea LIKE '% Feat %' THEN SUBSTR(namea, 1, INSTR(namea, ' Feat '))  \n"
	 * +
	 * "            WHEN namea LIKE '% Ft.%' THEN SUBSTR(namea, 1, INSTR(namea, ' Ft.'))  \n"
	 * +
	 * "            WHEN namea LIKE '% Feat.%' THEN SUBSTR(namea, 1, INSTR(namea, ' Feat.'))  \n"
	 * +
	 * "            WHEN namea LIKE '% , %' THEN SUBSTR(namea, 1, INSTR(namea, ' , ')-1) \n"
	 * +
	 * "            WHEN namea LIKE '%, %' THEN SUBSTR(namea, 1, INSTR(namea, ', ')-1) \n"
	 * + "            ELSE namea \n" + "        END AS nom,\n" + "        date1,\n"
	 * + "        date2,\n" + "        content,\n" + "        ttc,\n" +
	 * "        quantite,\n" + "        part_smart,\n" + "        tax_telecom,\n" +
	 * "        part_TTC,\n" + "        htva,\n" + "        part_artiste\n" +
	 * "    FROM \n" + "        details \n" + "    WHERE \n" +
	 * "        file = 'Orange'\n" + ") AS subquery\n" + "GROUP BY \n" +
	 * "    nom, date1, date2, content\n" + "ORDER BY \n" +
	 * "    ROUND(SUM(ttc), 3) DESC;\n" + "") List<Object[]> statArtisteOrange();
	 */

	/*
	 * @Query(nativeQuery = true, value =
	 * "select nom , round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
	 * +
	 * "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
	 * +
	 * "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
	 * + "from (\n" +
	 * "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
	 * + "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
	 * +
	 * "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
	 * + "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
	 * +
	 * "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
	 * + "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n" +
	 * "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n" +
	 * "else namea end)as nom,namea,  \n" +
	 * "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
	 * + "from orange    \n" + ")xx group by nom")
	 */

	@Query(nativeQuery = true, value = "select date1, namea as namea, content, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n" + "from (\n"
			+ "select     (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "when namea like '% Ft.%' then SUBSTR(namea, 1,  INSTR(namea,' Ft.'))  \n"
			+ "when namea like '% Feat.%' then SUBSTR(namea, 1,  INSTR(namea,' Feat.'))  \n"
			+ "when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "else namea end)as nom,namea,  \n"
			+ "date1 as date1,content as content, ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste\n"
			+ "from details where file = 'Orange'    \n" + ")xx group by nom order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statArtisteOrangeExcel();

/*	@Query(nativeQuery = true, value = "SELECT * FROM chanson_stat_view")
	List<Object[]> statChansonOrange();*/
	@Query(
  nativeQuery = true,
  value = "SELECT date1, date2, content, " +
          "ROUND(SUM(ttc), 3) AS ttc, SUM(quantite) AS quantite, " +
          "ROUND(SUM(part_smart), 3) AS part_smart, ROUND(SUM(tax_telecom), 3) AS tax_telecom, " +
          "ROUND(SUM(part_TTC), 3) AS part_TTC, ROUND(SUM(htva), 3) AS htva, " +
          "ROUND(SUM(part_artiste), 3) AS part_artiste, namea " +
          "FROM ( " +
          "  SELECT date1, date2, content, ttc, quantite, part_smart, tax_telecom, part_TTC, htva, part_artiste, namea " +
          "  FROM details " +
          "  WHERE file = 'Orange' " +
          "  UNION ALL " +
          "  SELECT NULL AS date1, NULL AS date2, ch.nom AS content, 0, 0, 0, 0, 0, 0, 0, u.n_artistique AS namea " +
          "  FROM chanson ch " +
          "  LEFT JOIN artiste u ON u.id = ch.idartiste_chasnon " +
          ") AS xx " +
          "GROUP BY date1, date2, content, namea " +
          "ORDER BY ttc DESC"
)
List<Object[]> statChansonOrange();

	/*@Query(
  nativeQuery = true,
  value = "SELECT content, SUM(ttc) AS ttc, SUM(quantite) AS quantite, SUM(part_smart) AS part_smart, " +
          "SUM(tax_telecom) AS tax_telecom, SUM(part_TTC) AS part_TTC, " +
          "SUM(htva) AS htva, SUM(part_artiste) AS part_artiste, namea " +
          "FROM ( " +
          "  SELECT content, SUM(ttc) AS ttc, SUM(quantite) AS quantite, SUM(part_smart) AS part_smart, " +
          "         SUM(tax_telecom) AS tax_telecom, SUM(part_TTC) AS part_TTC, " +
          "         SUM(htva) AS htva, SUM(part_artiste) AS part_artiste, namea " +
          "  FROM details WHERE file = 'Orange' GROUP BY content, namea " +
          "  UNION ALL " +
          "  SELECT ch.nom AS content, 0, 0, 0, 0, 0, 0, 0, u.n_artistique AS namea " +
          "  FROM chanson ch LEFT JOIN artiste u ON u.id = ch.idartiste_chasnon " +
          ") AS xx " +
          "GROUP BY content, namea " +
          "ORDER BY ttc DESC"
)
List<Object[]> statChansonOrange();*/

	/*
	 * @Query(nativeQuery = true, value
	 * ="select date1, date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite, round(sum(part_smart),3) as part_smart,\n"
	 * +
	 * "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
	 * +
	 * "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste, namea\n"
	 * + "from (\n" +
	 * "    select date1, date2, content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite, round(sum(part_smart),3) as part_smart,\n"
	 * +
	 * "    round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
	 * +
	 * "    round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste, namea\n"
	 * + "    from details dt where file = 'Orange' \n" +
	 * "    group by date1, date2, content, namea -- Inclure la date dans le GROUP BY\n"
	 * + "    UNION ALL\n" +
	 * "    select ch.nom as content, 0 as date1, 0 as date2, 0 as ttc, 0 as quantite, 0 as part_smart,\n"
	 * + "    0 as tax_telecom, 0 as part_TTC,\n" +
	 * "    0 as htva, 0 as part_artiste, u.n_artistique as namea\n" +
	 * "    from chanson ch \n" +
	 * "    left join artiste u on u.id = ch.idartiste_chasnon  \n" + ") xx\n" +
	 * "group by date1, date2, content, namea\n" + "") List<Object[]>
	 * statChansonOrange();
	 */

	@Query(nativeQuery = true, value = "select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom (\n    select  content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\nround(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\nround(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,namea\nfrom details where file = 'Orange' dt\n group by  content,namea\nUNION ALL\n select  ch.nom as content, 0 as ttc, 0 as quantite,0 as part_smart,\n0 as tax_telecom, 0 as part_TTC,\n0 as htva, 0 as part_artiste, u.n_artistique as namea\nfrom chanson ch \nleft join artiste u on u.id = ch.idartiste_chasnon  \n    )xx\n      group by content,namea  order by round((sum(ttc)),3) desc \n ")
	List<OrangeExcel> statChansonOrangeExcel();

	@Query(nativeQuery = true, value = "\r\n"
			+ " select  plateforme, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from details where file = 'Orange'   group by  plateforme order by round((sum(ttc)),3) desc")
	List<Object[]> statPlateformeOrange();

	@Query(nativeQuery = true, value = "\r\n"
			+ " select  plateforme, round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ "from details where file = 'Orange'   group by  plateforme order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statPlateformeOrangeExcel();

	@Query(nativeQuery = true, value = "select  date1,date2,category, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ " from details where file = 'Orange'  group by  category order by round((sum(ttc)),3) desc")
	List<Object[]> statcategoryOrange();

	@Query(nativeQuery = true, value = "select  category, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ " round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ " round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste\n"
			+ " from details where file = 'Orange'  group by  category order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statcategoryOrangeExcel();

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
			+ "from details where file = 'Orange'   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<Object[]> statRevenu();

	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
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
			+ "from details where file = 'Orange'   )xx group by date1,date2,nom,paye order by round((sum(ttc)),3) desc")
	List<OrangeExcel> statRevenuExcel();

	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
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
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "from details where file = 'Orange'   and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye")
	List<Object[]> HistRevenuOrange(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select namea ,date1,date2, round((sum(`ttc`)),3) as ttc,sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
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
			+ "ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "from details where file = 'Orange'  and namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ ")xx group by date1,date2,nom,paye")
	List<OrangeExcel> HistRevenuOrangeExcel(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and namea like concat(:id,'%') \n"
			+ "group by  date1,date2 order by round((sum(ttc)),3) desc ")
	List<Object[]> statDateArtisteByname(@Param("id") String id);

	@Modifying
	@Transactional
	@Query(value = "DELETE from details d  where file = 'Orange'  and d.date1=:date1 and d.date2=:date2 and d.file=:file")
	void deleteByDateFichier(@Param("date1") Date date1, @Param("date2") Date date2, @Param("file") String file);

	@Query(nativeQuery = true, value = "select  date1,date2, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart, round(sum(tax_telecom),3) as tax_telecom, \n"
			+ "round(sum(part_TTC),3) as part_TTC, round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste \n"
			+ "from details where file = 'Orange' and  content =:nom\n"
			+ "group by  date1,date2 order by round((sum(ttc)),3) desc")
	List<Object[]> statDateChansonByNom(@Param("nom") String nom);

	@Query(nativeQuery = true, value = "select (case when round((sum(part_artiste)),3) is null then 0 else  round((sum(part_artiste)),3) end)  as part_artiste\n"
			+ "from details where file = 'Orange' dd and namea  LIKE CONCAT((select n_artistique FROM user u where u.id=:id),'%')\n"
			+ "and dd.date1 between :datedebut and :datefin and dd.date2 between :datedebut and :datefin")
	List<Double> rapportStatTotalUsersById(@Param("id") Integer id, @Param("datedebut") Date datedebut,
			@Param("datefin") Date datefin);

	@Query(nativeQuery = true, value = "select  namea, round((sum(`ttc`)),3) as ttc, sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(`htva`),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from orange  group by    namea ORDER BY  round(sum(ttc),3) desc limit 5")
	List<Object[]> topArtisteOrange();

	@Query(nativeQuery = true, value = "select  namea,content, round((sum(ttc)),3) as ttc, sum(quantite) as quantite,round(sum(part_smart),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where file = 'Orange' group by  content order by  round(sum(ttc),3) desc limit 10")
	List<Object[]> topChansonOrange();

	// topDateOrange

	@Query(nativeQuery = true, value = "select  date1,date2,round((sum(`ttc`)),3) as ttc,   sum(quantite) as quantite,round(sum(`part_smart`),3) as part_smart,\n"
			+ "round(sum(tax_telecom),3) as tax_telecom, round(sum(part_TTC),3) as part_TTC,\n"
			+ "round(sum(htva),3) as htva, round(sum(part_artiste),3) as part_artiste,round(sum(totalnetderedevance),3) as totalnetderedevance,round(sum(par_smat_ht),3) as par_smat_ht\n"
			+ "from details where file = 'Orange' group by date1,date2 desc limit 10")
	List<Object[]> topDateOrange();

	@Query(nativeQuery = true, value = "select  content as name, round((sum(part_artiste)),3) as value from details where file = 'Orange' group by content order by round((sum(part_artiste)),3) desc limit 5")
	List<Map<String, Object>> ChartsChansOrange();

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
			+ "			from details where file = 'Orange'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =0 \n"
			+ "			)xx group by  nom\n" + "union ALL\n"
			+ "select nom , 0 as total,round(sum(part_artiste),3) as balance_paye ,0 as balance_n_paye  from (\n"
			+ "			select       (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "			when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "			when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "			when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "			when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "			else namea end)as nom,namea, \n"
			+ "			ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "			from details where file = 'Orange'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') and paye =1\n"
			+ "			)xx group by  nom\n" + "            union ALL\n"
			+ "select nom ,round(sum(part_artiste),3) as total,0 as balance_paye ,0 as balance_n_paye  from (\n"
			+ "			select       (case when namea like \"% Ft %\" then SUBSTR(namea, 1,  INSTR(namea,\" Ft \"))\n"
			+ "			when namea like '% Et %' then SUBSTR(namea, 1,  INSTR(namea,' Et '))  \n"
			+ "			when namea like '% Feat %' then SUBSTR(namea, 1,  INSTR(namea,' Feat '))  \n"
			+ "			when namea like '% , %' then SUBSTR(namea, 1,  INSTR(namea,' , ')-1) \n"
			+ "			when namea like '%, %' then SUBSTR(namea, 1,  INSTR(namea,', ')-1) \n"
			+ "			else namea end)as nom,namea, \n"
			+ "			ttc  as ttc,   quantite,  part_smart,  tax_telecom,  part_TTC, htva,  part_artiste,paye\n"
			+ "			from details where file = 'Orange'  and  namea  LIKE CONCAT((select n_artistique FROM artiste u where u.id=:id),'%') \n"
			+ "			)xx group by  nom\n" + "    ) balance group by nom ")
	List<Map<String, Object>> ChartsPartsRevenuOrange(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "select  (case when (select pays from pays where abreviation =ds.pays) is null then ds.pays else \n"
			+ "(select pays from pays where abreviation =ds.pays)  end)  as value, \n"
			+ "round((sum(part_artiste)),3) as name from details where file = 'Orange' ds group by pays order by round((sum(part_artiste)),3) desc limit 6")
	List<Map<String, Object>> ChartsPaysOrange();

	@Query(nativeQuery = true, value = "select date_format(date1, '%m / %Y') as name, round((sum(part_artiste)),3) as value from details where file = 'Orange' group by date1 order by date1 desc limit 5")
	List<Map<String, Object>> ChartsDateOrange();

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(value = "DELETE from orange d where d.date1=:date1 and d.file=:file")
	 * void deleteByDateFichierOrange(@Param("date1") Date date1,@Param("file")
	 * String file);
	 */
}
