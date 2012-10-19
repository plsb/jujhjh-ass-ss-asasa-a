select  
  (select nome from municipio limit 1) municipio,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  
--Diabeticos cadastradas por microárea
  sum((case when 
  (select distinct idmd5 from familiar where diabestes is true and idmd5 = fa.idmd5) is null then
  0 else 1 end))dia_cadastradas,

--Hipertensos cadastradas por microárea
  sum((case when 
  (select distinct idmd5 from familiar where hipertensao is true and idmd5 = fa.idmd5) is null then
  0 else 1 end))Hiper_cadastrados,

--Tuberculosos cadastradas por microárea
  sum((case when 
  (select distinct idmd5 from familiar where tuberculose is true and idmd5 = fa.idmd5) is null then
  0 else 1 end))Tub_cadastrados,

--Hanseniase cadastradas por microárea
  sum((case when 
  (select distinct idmd5 from familiar where hanseniase is true and idmd5 = fa.idmd5) is null then
  0 else 1 end))Hans_cadastrados,

    
--Diabeticos que tem acompanhamentos cadastrados
  sum((case when 
  (select distinct idmd5familiar from diabetes where idmd5familiar = fa.idmd5) is null then
  0 else 1 end))dia_acompanhados,

--Hipertensos que tem acompanhamentos cadastrados
  sum((case when 
  (select distinct idmd5familiar from hipertensao where idmd5familiar = fa.idmd5) is null then
  0 else 1 end))Hiper_acompanhados,

--Tuberculosos que tem acompanhamentos cadastrados
  sum((case when 
  (select distinct idmd5familiar from tuberculose where idmd5familiar = fa.idmd5) is null then
  0 else 1 end))Tub_acompanhados,

--Hanseniase que tem acompanhamentos cadastrados
  sum((case when 
  (select distinct idmd5familiar from hanseniase where idmd5familiar = fa.idmd5) is null then
  0 else 1 end))Hans_acompanhados
  
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
--where ar.codigo = 1
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao
order by ma.descricao



-------------------------------------------------------------- 

select  * from hanseniase
