package cn.itcast.hotel.constants;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/19 0019 12:27
 * @description： 这是描述
 * @modified By：
 */
public class EsContstants {

    public final static String HOTEL_MAPPING = "{\n" +
            "\t\"mappings\": {\n" +
            "\t\t\"properties\": {\n" +
            "\t\t\t\"id\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"name\": {\n" +
            "\t\t\t\t\"type\": \"text\",\n" +
            "\t\t\t\t\"analyzer\": \"ik_max_word\",\n" +
            "\t\t\t\t\"copy_to\": \"all\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"address\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"index\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t\"price\": {\n" +
            "\t\t\t\t\"type\": \"integer\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"score\": {\n" +
            "\t\t\t\t\"type\": \"integer\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"brand\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t\"city\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"starName\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"business\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"copy_to\": \"all\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"location\": {\n" +
            "\t\t\t\t\"type\": \"geo_point\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"pic\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"index\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t\"all\": {\n" +
            "\t\t\t\t\"type\": \"text\",\n" +
            "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    public final static String HOTEL_MAPPING_1 = "{\n" +
            "\t\"mappings\": {\n" +
            "\t\t\"properties\": {\n" +
            "\t\t\t\"id\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"name\": {\n" +
            "\t\t\t\t\"type\": \"text\",\n" +
            "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"address\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"index\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t\"price\": {\n" +
            "\t\t\t\t\"type\": \"integer\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"score\": {\n" +
            "\t\t\t\t\"type\": \"integer\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"brand\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"copy_to\": \"all\"\n" +
            "\t\t\t},\n" +
            "\n" +
            "\t\t\t\"city\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"starName\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"business\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"copy_to\": \"all\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"location\": {\n" +
            "\t\t\t\t\"type\": \"geo_point\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"pic\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"index\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t\"all\": {\n" +
            "\t\t\t\t\"type\": \"text\",\n" +
            "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}\n";



    public   final  static   String  HOTEL_MAPPING_2="{\n" +
            "\t\"mappings\": {\n" +
            "\t\t\"properties\": {\n" +
            "\t\t\t\"id\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"name\": {\n" +
            "\t\t\t\t\"type\": \"text\",\n" +
            "\t\t\t\t\"copy_to\": \"all\",\n" +
            "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"address\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"index\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t\"price\": {\n" +
            "\t\t\t\t\"type\": \"integer\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"score\": {\n" +
            "\t\t\t\t\"type\": \"integer\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"brand\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"copy_to\": \"all\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"city\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"starName\": {\n" +
            "\t\t\t\t\"type\": \"keyword\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"business\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"copy_to\": \"all\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"location\": {\n" +
            "\t\t\t\t\"type\": \"geo_point\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"pic\": {\n" +
            "\t\t\t\t\"type\": \"keyword\",\n" +
            "\t\t\t\t\"index\": false\n" +
            "\t\t\t},\n" +
            "\t\t\t\"all\": {\n" +
            "\t\t\t\t\"type\": \"text\",\n" +
            "\t\t\t\t\"analyzer\": \"ik_max_word\"\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

}