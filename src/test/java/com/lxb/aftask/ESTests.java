package com.lxb.aftask;



import com.alibaba.fastjson.JSON;
import com.lxb.aftask.domain.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;


import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.cluster.metadata.MetadataIndexTemplateService;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootTest
class ESTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
private User user;

    @Test
    void contextLoads() throws IOException {
       getDoc();

    }
//    操作文档
    void insertDoc() throws IOException {
//        向指定的索引插入文档
        User user = new User("lxb3", 23, "打杂", "不很高兴");
//        首先获取要操作的文档
        IndexRequest getRequest = new IndexRequest("lxb_index");
        getRequest.id("3");

        getRequest.source(JSON.toJSONString(user), XContentType.JSON);
//        getRequest.source(JSON.toJSON(user));
        IndexResponse indexResponse = restHighLevelClient.index(getRequest, RequestOptions.DEFAULT);
    }
    void delDoc(String delId) throws IOException {
        DeleteRequest getIndexRequest = new DeleteRequest("lxb_index");
        getIndexRequest.id(delId);
        DeleteResponse delete = restHighLevelClient.delete(getIndexRequest, RequestOptions.DEFAULT);

    }
    void updateDoc() throws IOException {
        User user = new User("不知道", 2, "没工作", "不挣钱");
        UpdateRequest lxb_index = new UpdateRequest("lxb_index", "1");
        lxb_index.doc(JSON.toJSONString(user),XContentType.JSON);

        UpdateResponse updateResponse = restHighLevelClient.update(lxb_index, RequestOptions.DEFAULT);

    }
    String  getDoc() throws IOException {
        GetRequest getRequest = new GetRequest("lxb_index","1");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields);
        System.out.println(documentFields.getSource());
        return documentFields.getSourceAsString();
    }
    boolean createIndex(String indexName) throws IOException {
        //        创建索引
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }
    boolean delIndex(String indexName) throws IOException {
//        删除索引
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }
    boolean isExitIndex(String indexName) throws IOException {
//        验证索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        return exists;
    }
    void translate(){
        RestTemplate restTemplate= new RestTemplate();
        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String badStr="\n";
//        JSONPObject jsonpObject= new JSONPObject();
        String str = "{\n" +
                "\t\"srcl\": \"nru\",\n" +
                "\t\"tgtl\": \"nzh\",\n" +
                "\t\"text\": \"\\n<img src='https://img.gazeta.ru/files3/417/14257417/TASS_17144519-pic_32ratio_900x600-900x600-39199.jpg'>\\n\\n\\n\\n\\n\\nРоссийская армия не всостоянии вести продолжительную войну натерритории Европы из-за ограниченных логистических возможностей. Об этом пишет американское издание War on the Rocks. Ключевая проблема &ndash; состояние железнодорожного полотна вРоссии. Военный обозреватель «Газеты.Ru» Михаил Ходаренок разбирался насколько справедливы подобные утверждения. \\n\\n\\nn=\\\"true\\\" class=\\\"_s_banner_inread\\\" >\\n\\nГлавный тезис статьи &ndash; ни одна другая европейская страна не использует железные дороги так, как российская армия. Нюанс, помнению издания, заключается втом, что российские железные дороги шире западноевропейской колеи. Только страны бывшего Советского Союза и Финляндия досих пор используют российский железнодорожный стандарт, втом числе страны Балтии, пишет издание.\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n \\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n                                                          \\n\\n\\n                               \\n                                                                                                                    \\n                 \\n                                      \\n       \\n\\n\\n\\n\\n\\n\\n\\n\\n\\n<img src=\\\"https://img.gazeta.ru/files3/889/14244889/RIAN_6703417.HR-pic_32ratio_600x400-600x400-13125.jpg\\\">\\n\\n\\n\\n\\n\\nНаводчик для&laquo;Циркона&raquo;. Россия запустила военный спутник наорбиту\\n\\n\\n\\nРакета-носитель «Союз-2.1б» вывела наорбиту Земли разгонный блок «Фрегат» своенным аппаратом. Пуск...\\n\\n\\n25 ноября 17:41\\n\\n\\n\\n\\n\\n\\n \\n\\n\\n\\n\\n\\n\\n                                          \\n \\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\nЭто означает, что возможности обеспечения устойчивости железных дорог российской армии заканчиваются награницах бывшего Советского Союза. Попытка пополнить запасы материальных средств российской армии запределами железнодорожной сети российской колеи вынудила бы их полагаться восновном нагрузовые автомобили, пока железнодорожные войска не смогли перенастроить/отремонтировать железную дорогу или построить новую, пишет издание.\\n«Однако уроссийской армии не хватает грузовых автомобилей дляудовлетворения своих логистических потребностей нарасстоянии более 90миль от складов снабжения», &ndash; считают вWar on the Rocks.\\nНа самом деле даже всоветские времена наступательные операции планировались наглубину до400км, а их продолжительность достигала до20суток. Расход материальных средств приэтом мог достигать 2,5млн тонн. Все эти вопросы, втом числе материального обеспечения, отрабатывались и отрабатываются сегодня насоответствующих учениях.\\n\\nn=\\\"true\\\" class=\\\"_s_banner_native3\\\">\\n\\nНикаких принципиальных проблем втыловом обеспечении войск приэтом не возникало.\\n«Политика свершившихся фактов требует, чтобы российские войска захватили страны Балтии и ликвидировали все сопротивление менее чем за96часов &ndash; дотого, как оперативная группа высокой готовности НАТО сможет подкрепить обороняющихся. Дальше Россия пройти не сможет», &ndash; утверждает издание.\\nПри этом авторами War on the Rocks не учитывается опыт даже Великой Отечественной войны. Например, назавершающем этапе боевых действий железнодорожные войска Красной Армии перешивали западноевропейскую колею наотечественный стандарт стемпом до30км всутки. \\nС учетом современного уровня оснащения железнодорожных бригад российской армии темпы перешивки западноевропейской колеи будут еще больше.\\nК слову говоря, даже посовременным нормам наступательная операция считается успешной, если она осуществляется стемпом до20км/сут. Таким образом, железные дороги успеют занаступающими войсками.\\nМнение автора может не совпадать спозицией редакции «Газеты.Ru».\\nБиография автора:\\nМихаил Михайлович Ходаренок &mdash; полковник вотставке.\\nОкончил Минское высшее инженерное зенитное ракетное училище (1976),\\nВоенную командную академию ПВО (1986).\\nКомандир зенитного ракетного дивизиона С-75 (1980&ndash;1983).\\nЗаместитель командира зенитного ракетного полка (1986&ndash;1988).\\nСтарший офицер Главного штаба Войск ПВО (1988&ndash;1992).\\nОфицер главного оперативного управления Генерального штаба (1992&ndash;2000).\\nВыпускник Военной академии Генерального штаба Вооруженных сил России (1998).\\nОбозреватель «Независимой газеты» (2000&ndash;2003), главный редактор газеты «Военно-промышленный курьер» (2010&ndash;2015).\"\n" +
                "}";
        String json = new String("{\"srcl\": \"nru\",\"tgtl\": \"nzh\",\"text\": \"https://img.gazeta.r\"}");

        System.out.println(json);
        HttpEntity<String> httpEntity = new HttpEntity<String>(str,httpHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity("http://192.168.52.3/new/translate",httpEntity,String.class);
        System.out.println(result.getBody().toString());
    }

}
