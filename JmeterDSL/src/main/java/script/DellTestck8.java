package script;

import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import us.abstracta.jmeter.javadsl.core.assertions.DslResponseAssertion;
import us.abstracta.jmeter.javadsl.core.controllers.DslTransactionController;
import us.abstracta.jmeter.javadsl.core.postprocessors.DslJsonExtractor;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class DellTestck8 {
    public static DslTransactionController get() {
        return transaction("TC_DellTestck8",
                        httpSampler("<_/tickets/_filter_Duplicate", "${protocol}://${host}:${port}/tickets/")
                                .header("Referer", "${protocol}://${host}:${port}/tickets/")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("Upgrade-Insecure-Requests", "1")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                .rawParam("status", "5")
                                .rawParam("date_from", "")
                                .rawParam("date_to", "")
                                .rawParam("q", "")
                                .children(
                                        regexExtractor("query_encoded", "'query_encoded' value='(.*?)'/>")
                                                .matchNumber(0)
                                                .defaultValue("query_encoded_ERROR")
                                ),
                        httpSampler("<_/datatables_ticket_list/", "${protocol}://${host}:${port}/datatables_ticket_list/${query_encoded}")
                                .header("Referer", "${protocol}://${host}:${port}/tickets/?status=5&date_from=&date_to=&q=")
                                .header("Accept-Language", "ru,en;q=0.9")
                                .header("X-Requested-With", "XMLHttpRequest")
                                .header("Accept-Encoding", "gzip, deflate")
                                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                .rawParam("draw", "1")
                                .param("columns[0][data]", "id")
                                .param("columns[0][name]", "")
                                .param("columns[0][searchable]", "true")
                                .param("columns[0][orderable]", "false")
                                .param("columns[0][search][value]", "")
                                .param("columns[0][search][regex]", "false")
                                .param("columns[1][data]", "ticket")
                                .param("columns[1][name]", "")
                                .param("columns[1][searchable]", "true")
                                .param("columns[1][orderable]", "true")
                                .param("columns[1][search][value]", "")
                                .param("columns[1][search][regex]", "false")
                                .param("columns[2][data]", "priority")
                                .param("columns[2][name]", "")
                                .param("columns[2][searchable]", "true")
                                .param("columns[2][orderable]", "true")
                                .param("columns[2][search][value]", "")
                                .param("columns[2][search][regex]", "false")
                                .param("columns[3][data]", "queue")
                                .param("columns[3][name]", "")
                                .param("columns[3][searchable]", "true")
                                .param("columns[3][orderable]", "true")
                                .param("columns[3][search][value]", "")
                                .param("columns[3][search][regex]", "false")
                                .param("columns[4][data]", "status")
                                .param("columns[4][name]", "")
                                .param("columns[4][searchable]", "true")
                                .param("columns[4][orderable]", "true")
                                .param("columns[4][search][value]", "")
                                .param("columns[4][search][regex]", "false")
                                .param("columns[5][data]", "created")
                                .param("columns[5][name]", "")
                                .param("columns[5][searchable]", "true")
                                .param("columns[5][orderable]", "true")
                                .param("columns[5][search][value]", "")
                                .param("columns[5][search][regex]", "false")
                                .param("columns[6][data]", "due_date")
                                .param("columns[6][name]", "")
                                .param("columns[6][searchable]", "true")
                                .param("columns[6][orderable]", "true")
                                .param("columns[6][search][value]", "")
                                .param("columns[6][search][regex]", "false")
                                .param("columns[7][data]", "assigned_to")
                                .param("columns[7][name]", "")
                                .param("columns[7][searchable]", "true")
                                .param("columns[7][orderable]", "true")
                                .param("columns[7][search][value]", "")
                                .param("columns[7][search][regex]", "false")
                                .param("columns[8][data]", "submitter")
                                .param("columns[8][name]", "")
                                .param("columns[8][searchable]", "true")
                                .param("columns[8][orderable]", "true")
                                .param("columns[8][search][value]", "")
                                .param("columns[8][search][regex]", "false")
                                .param("columns[9][data]", "time_spent")
                                .param("columns[9][name]", "")
                                .param("columns[9][searchable]", "true")
                                .param("columns[9][orderable]", "true")
                                .param("columns[9][search][value]", "")
                                .param("columns[9][search][regex]", "false")
                                .param("columns[10][data]", "kbitem")
                                .param("columns[10][name]", "")
                                .param("columns[10][searchable]", "true")
                                .param("columns[10][orderable]", "true")
                                .param("columns[10][search][value]", "")
                                .param("columns[10][search][regex]", "false")
                                .param("order[0][column]", "0")
                                .param("order[0][dir]", "asc")
                                .rawParam("start", "0")
                                .rawParam("length", "25")
                                .param("search[value]", "")
                                .param("search[regex]", "false")
                                .rawParam("_", "1720637714958")
                                .children(
                                        jsonExtractor("index", "$.data[*].id")
                                                .queryLanguage(DslJsonExtractor.JsonQueryLanguage.JSON_PATH)
                                                .matchNumber(0)
                                                .defaultValue("index_ERROR")
                                ),
                        ifController("\"${index}\" != \"index_ERROR\"",
                                httpSampler("<_/tickets/${index}/", "${protocol}://${host}:${port}/tickets/${index}/")
                                        .header("Referer", "${protocol}://${host}:${port}/tickets/")
                                        .header("Accept-Language", "ru,en;q=0.9")
                                        .header("Upgrade-Insecure-Requests", "1")
                                        .header("Accept-Encoding", "gzip, deflate")
                                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
                                httpSampler("<_/tickets/${index}/delete/", "${protocol}://${host}:${port}/tickets/${index}/delete/")
                                        .header("Referer", "${protocol}://${host}:${port}/tickets/17/")
                                        .header("Accept-Language", "ru,en;q=0.9")
                                        .header("Upgrade-Insecure-Requests", "1")
                                        .header("Accept-Encoding", "gzip, deflate")
                                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                        .children(
                                                regexExtractor("csrfmiddlewaretoken", "csrfmiddlewaretoken\\\" value=\\\"(.*?)\\\">")
                                                        .matchNumber(0)
                                                        .defaultValue("csrfmiddlewaretoken_ERROR")
                                        ),
                                httpSampler(">_/tickets/${index}/delete/", "${protocol}://${host}:${port}/tickets/${index}/delete/")
                                        .method(HTTPConstants.POST)
                                        .contentType(ContentType.APPLICATION_FORM_URLENCODED)
                                        .header("Referer", "${protocol}://${host}:${port}/tickets/17/delete/")
                                        .header("Accept-Language", "ru,en;q=0.9")
                                        .header("Origin", "${protocol}://${host}:${port}")
                                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                        .header("Upgrade-Insecure-Requests", "1")
                                        .header("Cache-Control", "max-age=0")
                                        .header("Accept-Encoding", "gzip, deflate")
                                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                        .rawParam("csrfmiddlewaretoken", "${csrfmiddlewaretoken}")
                                        .rawParam("next", "home"),
                                httpSampler("<_/tickets/${index}/", "${protocol}://${host}:${port}/tickets/${index}/")
                                        .header("Referer", "${protocol}://${host}:${port}/tickets/")
                                        .header("Accept-Language", "ru,en;q=0.9")
                                        .header("Upgrade-Insecure-Requests", "1")
                                        .header("Accept-Encoding", "gzip, deflate")
                                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 YaBrowser/24.6.0.0 Safari/537.36")
                                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                                        .children(
                                                responseAssertion()
                                                        .fieldToTest(DslResponseAssertion.TargetField.RESPONSE_CODE)
                                                        .ignoreStatus()
                                                        .matchesRegexes("404")
                                        )
                        )

        );
    }
}
