package lucas.online_bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Transacao;
import lucas.online_bank.model.ContaDTO;
import lucas.online_bank.model.TransacaoDTO;
import lucas.online_bank.repos.ContaRepository;
import lucas.online_bank.repos.TransacaoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


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

    public List<TransacaoDTO> findAll() {
        return transacaoRepository.findAll(Sort.by("idTransacao"))
                .stream()
                .map(transacao -> mapToDTO(transacao, new TransacaoDTO()))
                .collect(Collectors.toList());
    }

    public TransacaoDTO get(final Long idTransacao) {
        return transacaoRepository.findById(idTransacao)
                .map(transacao -> mapToDTO(transacao, new TransacaoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final TransacaoDTO transacaoDTO) {
        final Transacao transacao = new Transacao();
        mapToEntity(transacaoDTO, transacao);
        return transacaoRepository.save(transacao).getIdTransacao();
    }

    public void update(final Long idTransacao, final TransacaoDTO transacaoDTO) {
        final Transacao transacao = transacaoRepository.findById(idTransacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(transacaoDTO, transacao);
        transacaoRepository.save(transacao);
    }

    public void delete(final Long idTransacao) {
        transacaoRepository.deleteById(idTransacao);
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
    public List<TransacaoDTO> BuscaExtrato(Long idConta, LocalDate dataInicial, LocalDate dataFinal){
        Optional<Conta> conta = contaRepository.findById(idConta);
        return transacaoRepository.findByContaAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(conta.get(),dataInicial,dataFinal)
                .stream()
                .map(transacao -> mapToDTO(transacao, new TransacaoDTO()))
                .collect(Collectors.toList());
    }

}
