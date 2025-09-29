package br.com.scandianx.fastdev.components;

import br.com.scandianx.fastdev.dto.VideoRequestDTO;
import br.com.scandianx.fastdev.entity.NivelAcesso;
import br.com.scandianx.fastdev.repository.impl.VideoRepositoryImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class VideoInitializer {

    private final VideoFactory videoFactory;
    private final VideoRepositoryImpl videoRepository;

    public VideoInitializer(VideoFactory videoFactory, VideoRepositoryImpl videoRepository) {
        this.videoFactory = videoFactory;
        this.videoRepository = videoRepository;
    }

    @PostConstruct
    public void criarVideosSeNaoExistirem() {
        final String url = "https://youtube.com/watch?v=E96YS8IQSHs";

        class SeedVideo { String titulo; String desc; NivelAcesso nivel; SeedVideo(String t,String d,NivelAcesso n){titulo=t;desc=d;nivel=n;} }
        SeedVideo[] seeds = new SeedVideo[]{
            new SeedVideo("Introdução a Codemy",
                "Visão geral da plataforma, objetivos do curso e um tour pela aplicação. Vamos preparar o ambiente e entender como as peças se encaixam para acelerar o desenvolvimento.",
                NivelAcesso.FREE),
            new SeedVideo("Ambiente de Desenvolvimento e Setup",
                "Instalação de ferramentas, configuração do projeto, estrutura de pastas e boas práticas para manter o código organizado desde o início.",
                NivelAcesso.BRLDEV),
            new SeedVideo("Fundamentos de API REST",
                "Conceitos de recursos, verbos HTTP, status codes, tratamento de erros e modelagem de endpoints de forma consistente.",
                NivelAcesso.USDEV),
            new SeedVideo("Autenticação JWT na prática",
                "Geração e validação de tokens, filtros de segurança, rotas públicas e protegidas, além de estratégias de autorização.",
                NivelAcesso.BRLDEV),
            new SeedVideo("Integração com Banco de Dados",
                "Mapeamento JPA/Hibernate, estratégias de herança, consultas JPQL e paginação eficiente com Spring Data.",
                NivelAcesso.USDEV),
            new SeedVideo("Paginação, Ordenação e Filtros Avançados",
                "Criação de endpoints com paginação, ordenação multi-coluna e filtros dinâmicos, mantendo performance e legibilidade.",
                NivelAcesso.BRLDEV),
            new SeedVideo("Upload e Armazenamento de Arquivos",
                "Recebimento de arquivos, validações, limites de tamanho, armazenamento local e considerações para cloud.",
                NivelAcesso.USDEV),
            new SeedVideo("Testes Automatizados com Spring",
                "Testes de unidade e de integração, strategies de mock e como aumentar a confiabilidade sem perder produtividade.",
                NivelAcesso.BRLDEV),
            new SeedVideo("Observabilidade: Logs, Métricas e Tracing",
                "Boas práticas de logging, correlação de requisições, métricas úteis para produção e troubleshooting mais rápido.",
                NivelAcesso.USDEV),
            new SeedVideo("Deploy e Boas Práticas em Produção",
                "Empacotamento, variáveis de ambiente, profiles, hardening básico e checklist de produção para evitar surpresas.",
                NivelAcesso.PRIVATE)
        };

        for (SeedVideo s : seeds) {
            if (!videoRepository.existsByTituloIgnoreCase(s.titulo)) {
                VideoRequestDTO dto = new VideoRequestDTO();
                dto.setTipo("YOUTUBE");
                dto.setUrl(url);
                dto.setTitulo(s.titulo);
                dto.setDescricao(s.desc);
                dto.setNivelAcesso(s.nivel);
                videoFactory.createAndSaveVideo(dto);
            }
        }
    }
}
