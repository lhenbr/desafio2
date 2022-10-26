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


@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(final PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll(Sort.by("idPessoa"))
                .stream()
                .map(pessoa -> mapToDTO(pessoa, new PessoaDTO()))
                .collect(Collectors.toList());
    }

    public PessoaDTO get(final Long idPessoa) {
        return pessoaRepository.findById(idPessoa)
                .map(pessoa -> mapToDTO(pessoa, new PessoaDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PessoaDTO pessoaDTO) {
        final Pessoa pessoa = new Pessoa();
        mapToEntity(pessoaDTO, pessoa);
        return pessoaRepository.save(pessoa).getIdPessoa();
    }

    public void update(final Long idPessoa, final PessoaDTO pessoaDTO) {
        final Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(pessoaDTO, pessoa);
        pessoaRepository.save(pessoa);
    }

    public void delete(final Long idPessoa) {
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

    public boolean cpfExists(final String cpf) {
        return pessoaRepository.existsByCpfIgnoreCase(cpf);
    }

}
