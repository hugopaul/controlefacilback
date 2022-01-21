package br.com.controlefacil.dev.repository;

import br.com.controlefacil.dev.model.dashboards.BarDash;
import br.com.controlefacil.dev.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value = "select * from (\n" +
            "select case when a.months = 1 then 'Janeiro' \n" +
            "      \t\twhen a.months = 2 then 'Fevereiro'\n" +
            "     \t\twhen a.months = 3 then 'Março'\n" +
            "     \t\twhen a.months = 4 then 'Abril'\n" +
            "     \t\twhen a.months = 5 then 'Maio'\n" +
            "    \t\twhen a.months = 6 then 'Junho'\n" +
            "      \t\twhen a.months = 7 then 'Julho'\n" +
            "    \t\twhen a.months = 8 then 'Agosto'\n" +
            "    \t\twhen a.months = 9 then 'Setembro'\n" +
            "    \t\twhen a.months = 10 then 'Outubro'\n" +
            "     \t\twhen a.months = 11 then 'Novembro'\n" +
            "     \t\twhen a.months = 12 then 'Dezembro' end as months, \n" +
            "\t  b.count from (select extract(MONTH from generate_series\\:\\:DATE) as months from  generate_series((NOW() - INTERVAL '11 MONTH')\\:\\:DATE, current_date, INTERVAL'1 month') ) as a\n" +
            "\n" +
            "left join (select a.count, b.meses from \n" +
            "(select count(id) as count , extract(MONTH from a.data) as teste  from lancamento as a\n" +
            "where tipo_lancamento = :tipoLancamento group by teste )a\n" +
            "left join ( select extract(MONTH from generate_series\\:\\:DATE) as meses from  generate_series((NOW() - INTERVAL '11 MONTH')\\:\\:DATE, current_date, INTERVAL'1 month')\n" +
            " ) as b on a.teste = b.meses) as b on a.months = b.meses\n" +
            ") bardash",nativeQuery = true)
     List<BarDash> meseqtd(Long tipoLancamento);

    @Query(value = "select sum(valor) from lancamento where tipo_lancamento = 2 and data between NOW()- INTERVAL '30 DAY' and now()",nativeQuery = true)
    Long somaValorEntrada();
}