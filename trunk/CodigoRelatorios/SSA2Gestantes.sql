select  
  (select nome from municipio limit 1) municipio,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  
--Gestantes cadastradas por microárea
  sum((case when 
  (select distinct idmd5 from familiar f join gestante g on g.idmd5familiar = f.idmd5 where f.gestante is true and g.dtprovavelparto > (select current_date) and f.idmd5 = fa.idmd5) is null then
  0 else 1 end))ges_cadastradas,
  
--Gestantes que tem acompanhamentos cadastrados
  sum((case when 
  (select distinct idmd5familiar from gestante where dtprovavelparto > (select current_date) and idmd5familiar = fa.idmd5) is null then
  0 else 1 end))ges_acompanhadas,
  
--Total de gestante de fez consulta pré-natal no mês  
  sum((case when 
  (select distinct idmd5familiar from gestante where extract('Month' from dtconsulprenatal) = (Select Extract('Month' From Now())) and dtprovavelparto > (select current_date) and idmd5familiar = fa.idmd5) is null then
  0 else 1 end))total_prenatal,

--Total de gestante que fez consulta pré-natal iniciado no 1º Trimestre da gestação  
  sum((case when 
  (select distinct idmd5familiar from gestante where mesgestacao <= 3 and dtprovavelparto > (select current_date) and idmd5familiar = fa.idmd5) is null then
  0 else 1 end))total_prenatal_tri,	  

--Gestantes menores de 20 anos
  sum((case when 
  (select distinct idmd5familiar from gestante where frmeno20anos = true and dtprovavelparto > (select current_date) and idmd5familiar = fa.idmd5) is null then
  0 else 1 end))total_menos_20_anos,	

--Gestantes com vacina em dia
  sum((case when 
  (
   select distinct idfamiliar from vacinas
   where tipovacina = 'DUPLA ADULTO'  
   and (dataaplicacao between (select cast(current_date - interval '5 year' as date)) and (select current_date) and doseaplicada in ('1','2','3'))
   or  (cast(dataaplicacao + interval '5 year' as date) < (select current_date) and doseaplicada in ('1','2','3') 
	or (dataaplicacao between (select cast(current_date - interval '10 year' as date)) and (select current_date)  and tipovacina = 'DUPLA ADULTO' and doseaplicada in ('1','2','3','R'))
	and idfamiliar = fa.idmd5
       )
   or  ( idfamiliar in (select distinct idmd5familiar from gestante where dtprovavelparto > (select current_date)) and tipovacina = 'DUPLA ADULTO' and doseaplicada in ('1','2')
        and idfamiliar = fa.idmd5
       )  
   --or  (    
   --      idfamiliar in ((select distinct idmd5familiar from gestante where mesgestacao < 8 and dtprovavelparto > (select current_date)))
   --      and tipovacina = 'DUPLA ADULTO' and aplicada is true
   --      and doseaplicada not in ('2','3','R') 
   --    )     
  ) is null then
  0 else 1 end))total_vacina_em_dia 
  
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
--where ar.codigo = $P{area}
--and   sg.codigo = $P{segmento}
--and   un.codigo_sia_sus = $P{unidade}
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao
order by ma.descricao



-------------------------------------------------------------- 

/*select distinct idfamiliar from vacinas
where tipovacina = 'DUPLA ADULTO'  
and (dataaplicacao between (select cast(current_date - interval '5 year' as date)) and (select current_date) and doseaplicada in ('1','2','3'))
or  (cast(dataaplicacao + interval '5 year' as date) < (select current_date) and doseaplicada in ('1','2','3') 
	or (dataaplicacao between (select cast(current_date - interval '10 year' as date)) and (select current_date)  and tipovacina = 'DUPLA ADULTO' and doseaplicada in ('1','2','3','R'))
	and idfamiliar = '' 
    )
or  ( idfamiliar = (select distinct idmd5familiar from gestante where dtprovavelparto > (select current_date)) and tipovacina = 'DUPLA ADULTO' and doseaplicada in ('1','2')
      and idfamiliar = '' 	
    )    
or  (    
      idfamiliar = ((select distinct idmd5familiar from gestante where mesgestacao < 8 and dtprovavelparto > (select current_date)))
      and tipovacina = 'DUPLA ADULTO' 
      and doseaplicada not in ('2','3','R') and aplicada is true
     ) 

select * from vacinas*/
