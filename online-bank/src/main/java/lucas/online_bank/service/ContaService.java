package lucas.online_bank.service;

import java.util.List;
import java.util.stream.Collectors;
import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Pessoa;
import lucas.online_bank.model.ContaDTO;
import lucas.online_bank.repos.ContaRepository;
import lucas.online_bank.repos.PessoaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Classe que encapsula parte da logica de négocio relacionada as contas
 * @author Lucas Martins
 */
@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final PessoaRepository pessoaRepository;

    public ContaService(final ContaRepository contaRepository,
            final PessoaRepository pessoaRepository) {
        this.contaRepository = contaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Busca todas as contas cadastradas
     * @return List:ContaDTO, lista com todas as contas cadastradas no sistema
     */
    public List<ContaDTO> buscaTodos() {
        return contaRepository.findAll(Sort.by("idConta"))
                .stream()
                .map(conta -> mapToDTO(conta, new ContaDTO()))
                .collect(Collectors.toList());
    }

    /**
     * Busca uma conta com o id passado e retorna o seu DTO
     * @param idConta
     * @return ContaDTO, o DTO da conta buscada
     */
    public ContaDTO busca(final Long idConta) {
        return contaRepository.findById(idConta)
                .map(conta -> mapToDTO(conta, new ContaDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Cria uma conta de acordo com as informaçoes passadas via DTO
     * @param contaDTO
     * @return long, id da conta criada
     */
    public Long cria(final ContaDTO contaDTO) {
        final Conta conta = new Conta();
        mapToEntity(contaDTO, conta);
        return contaRepository.save(conta).getIdConta();
    }

    /**
     * modifica uma conta com o id fornecido de acordo com os dados fornecidos na contaDTO
     * @param idConta
     * @param contaDTO
     */
    public void modifica(final Long idConta, final ContaDTO contaDTO) {
        final Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(contaDTO, conta);
        contaRepository.save(conta);
    }
    private ContaDTO mapToDTO(final Conta conta, final ContaDTO contaDTO) {
        contaDTO.setIdConta(conta.getIdConta());
        contaDTO.setSaldo(conta.getSaldo());
        contaDTO.setLimiteSaqueDiario(conta.getLimiteSaqueDiario());
        contaDTO.setFlagAtivo(conta.getFlagAtivo());
        contaDTO.setTipoConta(conta.getTipoConta());
        contaDTO.setDataCriacao(conta.getDataCriacao());
        contaDTO.setPessoa(conta.getPessoa() == null ? null : conta.getPessoa().getIdPessoa());
        return contaDTO;
    }

    private Conta mapToEntity(final ContaDTO contaDTO, final Conta conta) {
        conta.setSaldo(contaDTO.getSaldo());
        conta.setLimiteSaqueDiario(contaDTO.getLimiteSaqueDiario());
        conta.setFlagAtivo(contaDTO.getFlagAtivo());
        conta.setTipoConta(contaDTO.getTipoConta());
        conta.setDataCriacao(contaDTO.getDataCriacao());
        final Pessoa pessoa = contaDTO.getPessoa() == null ? null : pessoaRepository.findById(contaDTO.getPessoa())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pessoa not found"));
        conta.setPessoa(pessoa);
        return conta;
    }
}
