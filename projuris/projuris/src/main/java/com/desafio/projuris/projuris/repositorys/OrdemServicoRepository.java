package com.desafio.projuris.projuris.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.projuris.projuris.basicas.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	//OrdemServico findByEtapaEqualsPendenteAndResponsavelByIdOrNomeIgnoreCase(Responsavel responsavel);
	
	@Query(value = "SELECT o FROM OrdemServico o WHERE o.etapa=2 AND o.responsavel.nome = :nome")
	List<OrdemServico> consultarOrdemPorEtapaPendenteEResponsavelPorNome(String nome);

}
