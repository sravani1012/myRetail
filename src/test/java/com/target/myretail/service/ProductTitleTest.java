package com.target.myretail.service;

import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.exception.ProductTitleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductTitleTest {

    ProductTitle productTitle = new ProductTitle();
    RestTemplate restTemplate = mock(RestTemplate.class);

    @Before
    public void setUp() throws Exception {
        productTitle.productUrl = "mockProductUrl";
        productTitle.productUrlExclusion = "mockExclusion";
        productTitle.restTemplate = restTemplate;
    }

    @Test
    public void testProductTitle() {
        String title = "{\"product\":{\"deep_red_labels\":{\"total_count\":2,\"labels\":[{\"id\":\"gqwm8i\",\"name\":\"TAC\",\"type\":\"relationship type\",\"priority\":0,\"count\":1},{\"id\":\"twbl94\",\"name\":\"Movies\",\"type\":\"merchandise type\",\"priority\":0,\"count\":1}]},\"available_to_promise_network\":{\"product_id\":\"13860428\",\"id_type\":\"TCIN\",\"available_to_promise_quantity\":11,\"street_date\":\"2011-11-15T06:00:00.000Z\",\"availability\":\"AVAILABLE\",\"online_available_to_promise_quantity\":11,\"stores_available_to_promise_quantity\":0,\"availability_status\":\"IN_STOCK\",\"multichannel_options\":[\"HOLD\",\"SHIPGUEST\"],\"is_infinite_inventory\":false,\"loyalty_availability_status\":\"IN_STOCK\",\"loyalty_purchase_start_date_time\":\"1970-01-01T00:00:00.000Z\",\"is_loyalty_purchase_enabled\":false},\"promotion\":{\"subscriptionShippingMessage\":\"free shipping\",\"promotionList\":[]},\"price\":{\"partNumber\":\"13860428\",\"channelAvailability\":\"0\",\"listPrice\":{\"minPrice\":0.0,\"maxPrice\":0.0,\"price\":19.98,\"formattedPrice\":\"$19.98\",\"priceType\":\"MSRP\"},\"offerPrice\":{\"minPrice\":0.0,\"maxPrice\":0.0,\"price\":14.99,\"formattedPrice\":\"$14.99\",\"priceType\":\"Reg\",\"saveDollar\":\"4.99\",\"savePercent\":\"25\",\"startDate\":1498374000000,\"endDate\":253402236000000},\"mapPriceFlag\":\"N\"},\"item\":{\"tcin\":\"13860428\",\"bundle_components\":{\"is_assortment\":false,\"is_kit_master\":false,\"is_standard_item\":true,\"is_component\":false},\"dpci\":\"058-34-0436\",\"upc\":\"025192110306\",\"product_description\":{\"title\":\"The Big Lebowski (Blu-ray)\",\"bullet_description\":[\"<B>Movie Genre:</B> Comedy\",\"<B>Software Format:</B> Blu-ray\",\"<B>Movie Studio:</B> Universal Studios\"],\"general_description\":\"Blu-ray BIG LEBOWSKI, THE Movies\"},\"parent_items\":\"46767107\",\"buy_url\":\"https://www.target.com/p/the-big-lebowski-blu-ray/-/A-13860428\",\"variation\":{},\"enrichment\":{\"images\":[{\"base_url\":\"https://target.scene7.com/is/image/Target/\",\"primary\":\"13860428\"}],\"sales_classification_nodes\":[{\"node_id\":\"yzuww\"},{\"node_id\":\"ieagq\"},{\"node_id\":\"5t0ak\"},{\"node_id\":\"5xswx\"},{\"node_id\":\"55ayu\"}]},\"return_method\":\"This item can be returned to any Target store or Target.com.\",\"handling\":{},\"recall_compliance\":{\"is_product_recalled\":false},\"tax_category\":{\"tax_class\":\"G\",\"tax_code_id\":99999,\"tax_code\":\"99999\"},\"display_option\":{\"is_size_chart\":false,\"is_warranty\":false},\"fulfillment\":{\"is_po_box_prohibited\":true,\"po_box_prohibited_message\":\"We regret that this item cannot be shipped to PO Boxes.\"},\"package_dimensions\":{\"weight\":\"0.18\",\"weight_unit_of_measure\":\"POUND\",\"width\":\"5.33\",\"depth\":\"6.65\",\"height\":\"0.46\",\"dimension_unit_of_measure\":\"INCH\"},\"environmental_segmentation\":{\"is_lead_disclosure\":false},\"manufacturer\":{},\"product_classification\":{\"product_type\":\"542\",\"product_type_name\":\"ELECTRONICS\",\"item_type_name\":\"Movies\",\"item_type\":{\"category_type\":\"Item Type: MMBV\",\"type\":300752,\"name\":\"Movies\"}},\"product_brand\":{\"brand\":\"Universal Home Video\"},\"item_state\":\"READY_FOR_LAUNCH\",\"specifications\":[],\"attributes\":{\"gift_wrapable\":\"N\",\"has_prop65\":\"N\",\"is_hazmat\":\"N\",\"max_order_qty\":10,\"street_date\":\"2011-11-15\",\"media_format\":\"Blu-ray\",\"merch_class\":\"MOVIES\",\"merch_subclass\":34,\"return_method\":\"This item can be returned to any Target store or Target.com.\"},\"country_of_origin\":\"US\",\"relationship_type_code\":\"Title Authority Child\",\"subscription_eligible\":false,\"ribbons\":[],\"tags\":[],\"estore_item_status_code\":\"A\",\"eligibility_rules\":{\"hold\":{\"is_active\":true},\"ship_to_guest\":{\"is_active\":true}},\"return_policies\":{\"user\":\"Regular Guest\",\"policyDays\":\"30\",\"guestMessage\":\"This item must be returned within 30 days of the ship date. See return policy for details.\"},\"gifting_enabled\":true}}}";
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.ok().body(title));
        String actualTitle = productTitle.getProductTitle(123);
        assertEquals("The Big Lebowski (Blu-ray)", actualTitle);
    }

    @Test(expected = ProductTitleNotFoundException.class)
    public void testProductTitleThrowsNotFoundException() {
        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        productTitle.getProductTitle(123);
    }

    @Test(expected = RuntimeException.class)
    public void testProductTitleThrowsRuntimeException() {
        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        productTitle.getProductTitle(123);
    }
}
