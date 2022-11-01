package lucas.online_bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Transacao;
import lucas.online_bank.model.TransacaoDTO;
import lucas.online_bank.repos.ContaRepository;
import lucas.online_bank.repos.TransacaoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Classe que encapsula parte da logica de négocio relacionada as transaçoes
 * @author Lucas Martins
 */
@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;
    private final ContaService contaService;

    public TransacaoService(final TransacaoRepository transacaoRepository,
            final ContaService contaService, final ContaRepository contaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaService = contaService;
        this.contaRepository = contaRepository;
    }
    /**
     * cria uma transação com base no transaçaoDTO recebida
     * @param transacaoDTO
     * @return Long, id da transação criada
     */
    public Long cria(final TransacaoDTO transacaoDTO) {
        final Transacao transacao = new Transacao();
        mapToEntity(transacaoDTO, transacao);
        return transacaoRepository.save(transacao).getIdTransacao();
    }

    /**
     * Função que busca todas as transaçoes feitas em uma conta em um determindado periodo/
     * @param idConta
     * @param dataInicial
     * @param dataFinal
     * @return List:TransacaoDTO
     */
    public List<TransacaoDTO> BuscaTransacoes(Long idConta, LocalDate dataInicial, LocalDate dataFinal){
        Optional<Conta> conta = contaRepository.findById(idConta);
        return transacaoRepository.findByContaAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(conta.get(),dataInicial,dataFinal)
                .stream()
                .map(transacao -> mapToDTO(transacao, new TransacaoDTO()))
                .collect(Collectors.toList());
    }

    private TransacaoDTO mapToDTO(final Transacao transacao, final TransacaoDTO transacaoDTO) {
        transacaoDTO.setIdTransacao(transacao.getIdTransacao());
        transacaoDTO.setValor(transacao.getValor());
        transacaoDTO.setDataTransacao(transacao.getDataTransacao());
        transacaoDTO.setConta(transacao.getConta() == null ? null : transacao.getConta().getIdConta());
        return transacaoDTO;
    }

    private Transacao mapToEntity(final TransacaoDTO transacaoDTO, final Transacao transacao) {
        transacao.setValor(transacaoDTO.getValor());
        transacao.setDataTransacao(transacaoDTO.getDataTransacao());
        final Conta conta = transacaoDTO.getConta() == null ? null : contaRepository.findById(transacaoDTO.getConta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "conta not found"));
        transacao.setConta(conta);
        return transacao;
    }

}
