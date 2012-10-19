select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Alcoolismo' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and alcolismo is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Alcoolismo' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and alcolismo is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Chagas' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and chagas is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Chagas' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and chagas is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Deficiente' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and deficiencia is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Deficiente' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and deficiencia is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Diabete' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and diabestes is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Diabete' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and diabestes is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Epilepsia' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and epilepsia is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Epilepsia' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and epilepsia is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Gestante' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and gestante is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Gestante' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and gestante is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Hipertensão' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and hipertensao is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Hipertensão' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and hipertensao is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Hanseníase' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and hanseniase is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Hanseníase' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and hanseniase is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Malária' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and malaria is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Malária' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and malaria is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Tuberculose' as condicao_referida,
  '0 a 14 Anos' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento <= (14*365)) and idmd5 = fa.idmd5 and tuberculose is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao

union all

select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'Tuberculose' as condicao_referida,
  '15 Anos ou Mais' as Faixa_Etaria,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento >= (15*365)) and idmd5 = fa.idmd5 and tuberculose is true) is null then
  0 else 1 end)) as total  
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
group by sg.codigo, un.codigo_sia_sus, ar.codigo, ma.descricao
