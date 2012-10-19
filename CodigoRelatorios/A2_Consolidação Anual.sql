--Masculino Menores de 1 ano
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '< 1' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento) < 365 and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 1 ano e 4 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '1 - 4' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > 365 and (select current_date) - datanascimento < (4 *365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 5 ano e 6 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '5 - 6' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (5*365) and (select current_date) - datanascimento < (6*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 7 ano e 9 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '7 - 9' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (7*365) and (select current_date) - datanascimento < (9*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 10 ano e 14 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '10 - 14' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (10*365) and (select current_date) - datanascimento < (14*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 15 ano e 19 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '15 - 19' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (15*365) and (select current_date) - datanascimento < (19*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 20 ano e 39 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '20 - 39' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (20*365) and (select current_date) - datanascimento < (39*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 40 ano e 49 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '40 - 49' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (40*365) and (select current_date) - datanascimento < (49*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Entre 50 ano e 59 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '50 - 59' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (50*365) and (select current_date) - datanascimento < (59*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

--Masculino Maior que 60
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '> 60' as faixa_etaria,
  'Masculino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (60*365)) and idmd5 = fa.idmd5 and sexo = 'M') is null then
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

-- F E M I N I N O

union all

--Feminino Menores de 1 ano
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '< 1' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento) < 365 and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 1 ano e 4 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '1 - 4' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > 365 and (select current_date) - datanascimento < (4 *365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 5 ano e 6 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '5 - 6' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (5*365) and (select current_date) - datanascimento < (6*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 7 ano e 9 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '7 - 9' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (7*365) and (select current_date) - datanascimento < (9*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 10 ano e 14 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '10 - 14' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (10*365) and (select current_date) - datanascimento < (14*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 15 ano e 19 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '15 - 19' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (15*365) and (select current_date) - datanascimento < (19*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 20 ano e 39 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '20 - 39' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (20*365) and (select current_date) - datanascimento < (39*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 40 ano e 49 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '40 - 49' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (40*365) and (select current_date) - datanascimento < (49*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Entre 50 ano e 59 anos
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '50 - 59' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (50*365) and (select current_date) - datanascimento < (59*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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

--Feminino Maior que 60
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  '> 60' as faixa_etaria,
  'Feminino' as Sexo,
  sum((case when 
  (select distinct idmd5 from familiar where ((select current_date) - datanascimento > (60*365)) and idmd5 = fa.idmd5 and sexo = 'F') is null then
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



