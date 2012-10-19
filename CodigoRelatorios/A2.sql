--Tipo de Casa
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'TIPO DE CASA' as titulo,
  re.tipocasa,
  (select count(id) from residencias r join area a on a.codigo_area = r.area where r.tipocasa = re.tipocasa and a.codigo = ar.codigo) total,
  (cast((select count(id) from residencias r join area a on a.codigo_area = r.area where r.tipocasa = re.tipocasa and a.codigo = ar.codigo) * 100.00 /
  (select count(*) from residencias r join area a on a.codigo_area = r.area where a.codigo = ar.codigo)as real))as percentual 
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao,re.tipocasa

union all 

--Destino do Lixo
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'DESTINO DO LIXO' as titulo,
  re.destlixo,
  (select count(id) from residencias r join area a on a.codigo_area = r.area where r.destlixo = re.destlixo and a.codigo = ar.codigo) total,
  (cast((select count(id) from residencias r join area a on a.codigo_area = r.area where r.destlixo = re.destlixo and a.codigo = ar.codigo) * 100.00 /
  (select count(*) from residencias r join area a on a.codigo_area = r.area where a.codigo = ar.codigo)as real))as percentual
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao,re.destlixo

union all 

--Tratamento de Água
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'TRATAMENTO DE ÁGUA' as titulo,
  re.tatamentoagua,
  (select count(id) from residencias r join area a on a.codigo_area = r.area where r.tatamentoagua = re.tatamentoagua and a.codigo = ar.codigo) total,
  (cast((select count(id) from residencias r join area a on a.codigo_area = r.area where r.tatamentoagua = re.tatamentoagua and a.codigo = ar.codigo) * 100.00 /
  (select count(*) from residencias r join area a on a.codigo_area = r.area where a.codigo = ar.codigo)as real))as percentual 
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao,re.tatamentoagua

union all 

--Abastecimento de Água
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'ABASTECIMENTO DE ÁGUA' as titulo,
  re.abastecimentoagua,
  (select count(id) from residencias r join area a on a.codigo_area = r.area where r.abastecimentoagua = re.abastecimentoagua and a.codigo = ar.codigo) total,
  (cast((select count(id) from residencias r join area a on a.codigo_area = r.area where r.abastecimentoagua = re.abastecimentoagua and a.codigo = ar.codigo) * 100.00 /
  (select count(*) from residencias r join area a on a.codigo_area = r.area where a.codigo = ar.codigo)as real))as percentual 
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao,re.abastecimentoagua

union all 

--Destino de Fezes/Urina
select  
  (select nome from municipio limit 1) municipio,
  (select uf from municipio limit 1) UF,
  (select codigo_ibge from municipio limit 1) codigo_ibge,
  sg.codigo as segmento,
  un.codigo_sia_sus as unidade,
  ar.codigo as area,
  ma.descricao,
  'DESTINO DE FEZES/URINA' as titulo,
  re.destfezes,
  (select count(id) from residencias r join area a on a.codigo_area = r.area where r.destfezes = re.destfezes and a.codigo = ar.codigo) total,
  (cast((select count(id) from residencias r join area a on a.codigo_area = r.area where r.destfezes = re.destfezes and a.codigo = ar.codigo) * 100.00 /
  (select count(*) from residencias r join area a on a.codigo_area = r.area where a.codigo = ar.codigo)as real))as percentual
from familiar fa
join residencias re on re.endereco = fa.rua and re.num_residencia = fa.numero
join microarea ma on ma.codigo_microarea = re.microarea
join area ar on ar.codigo_area = ma.idarea
join segmento sg on sg.codigo_segmento = ar.codigo_segmento
join unidade un on un.codigo_unidade = ar.idunidade
join bairro ba on ba.codigo_bairro = un.idbairro
group by sg.codigo,un.codigo_sia_sus,ar.codigo,ma.descricao,re.destfezes

