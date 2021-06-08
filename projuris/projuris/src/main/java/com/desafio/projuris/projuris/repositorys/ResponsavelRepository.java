package com.desafio.projuris.projuris.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.projuris.projuris.basicas.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{

}
