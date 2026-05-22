package br.com.projetovet.projeto_vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Pet;
import br.com.projetovet.projeto_vet.projection.PetProjection;
import br.com.projetovet.projeto_vet.repository.PetRepository;

@Service
public class PetCachingService {

    @Autowired
    private PetRepository repoP;

    // paginação

    @Cacheable(
            value = "retornarPetsPaginados",
            key = "#pr")
    public Page<Pet> findAll(PageRequest pr){

        return repoP.findAll(pr);
    }

    // substring

    @Cacheable(
            value = "retornarPetPorSubstring",
            key = "#substring")
    public List<PetProjection>
    retornarPetPorSubstring(String substring){

        return repoP.retornarPetPorSubstring(substring);
    }

    // por nome

    @Cacheable(
            value = "retornarPetPorNome",
            key = "#nome")
    public List<Pet> retornarPetPorNome(String nome){

        return repoP.retornarPetPorNome(nome);
    }

    // por raça

    @Cacheable(
            value = "retornarPetPorRaca",
            key = "#raca")
    public List<Pet> retornarPetPorRaca(String raca){

        return repoP.retornarPetPorRaca(raca);
    }

    // todos

    @Cacheable(value = "retornarTodosPets")
    public List<Pet> findAll(){

        return repoP.findAll();
    }

    // por id

    @Cacheable(
            value = "retornarPetPorId",
            key = "#id")
    public Optional<Pet> findById(Long id){

        return repoP.findById(id);
    }

    // remover cache

    @CacheEvict(
            value = {
                    "retornarPetsPaginados",
                    "retornarPetPorSubstring",
                    "retornarPetPorNome",
                    "retornarPetPorRaca",
                    "retornarTodosPets",
                    "retornarPetPorId"
            },
            allEntries = true)
    public void removerCache(){

        System.out.println("Cache removido!");
    }
}