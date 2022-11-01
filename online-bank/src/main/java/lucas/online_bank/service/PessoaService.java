package lucas.online_bank.service;

import java.util.List;
import java.util.stream.Collectors;
import lucas.online_bank.domain.Pessoa;
import lucas.online_bank.model.PessoaDTO;
import lucas.online_bank.repos.PessoaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Classe que encapsula parte da logica de négocio relacionada as pessoas
 * @author Lucas Martins
 */
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(final PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * funçãoq que busca todas as pessoas cadastradas no sistema
     * @return List:PessoaDTO, uma lista com todas as pessoas cadastradas no sistema
     */
    public List<PessoaDTO> buscaTodos() {
        return pessoaRepository.findAll(Sort.by("idPessoa"))
                .stream()
                .map(pessoa -> mapToDTO(pessoa, new PessoaDTO()))
                .collect(Collectors.toList());
    }

    /**
     * Busca uma pessoa com o id passado
     * @param idPessoa
     * @return PessoaDTO, pessoa com o id pesquisado
     */
    public PessoaDTO busca(final Long idPessoa) {
        return pessoaRepository.findById(idPessoa)
                .map(pessoa -> mapToDTO(pessoa, new PessoaDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * cria uma pessoa e a salva no repositotio de acordo com a pessoaDTO passada
     * @param pessoaDTO
     * @return
     */
    public Long cria(final PessoaDTO pessoaDTO) {
        final Pessoa pessoa = new Pessoa();
        mapToEntity(pessoaDTO, pessoa);
        return pessoaRepository.save(pessoa).getIdPessoa();
    }

    /**
     * Modifica uma pessoa com o id passado de acordo com as informaçoes fornecidas no DTO
     * @param idPessoa
     * @param pessoaDTO
     */
    public void modifica(final Long idPessoa, final PessoaDTO pessoaDTO) {
        final Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(pessoaDTO, pessoa);
        pessoaRepository.save(pessoa);
    }

    /**
     * deleta a pessoa com o id informado
     * @param idPessoa
     */
    public void exclui(final Long idPessoa) {
        pessoaRepository.deleteById(idPessoa);
    }

    private PessoaDTO mapToDTO(final Pessoa pessoa, final PessoaDTO pessoaDTO) {
        pessoaDTO.setIdPessoa(pessoa.getIdPessoa());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setCpf(pessoa.getCpf());
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());
        return pessoaDTO;
    }

    private Pessoa mapToEntity(final PessoaDTO pessoaDTO, final Pessoa pessoa) {
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        return pessoa;
    }
}
