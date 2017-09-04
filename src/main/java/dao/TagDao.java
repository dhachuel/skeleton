package dao;

import api.ReceiptResponse;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.ReceiptTagRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static generated.Tables.RECEIPTS;
import static generated.Tables.RECEIPT_TAG;


public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public void insert(String tag, int receipt_id) {
        // Test if tuple exists
        List<String> results = dsl.selectFrom(RECEIPT_TAG).where(RECEIPT_TAG.TAG.eq(tag).and(
             RECEIPT_TAG.RECEIPT_ID.equal(receipt_id))).fetch(RECEIPT_TAG.TAG);

        if(results.isEmpty()){
          ReceiptTagRecord tagRecord = dsl
            .insertInto(RECEIPT_TAG, RECEIPT_TAG.RECEIPT_ID, RECEIPT_TAG.TAG)
            .values(receipt_id, tag)
            .returning(RECEIPT_TAG.RECEIPT_ID, RECEIPT_TAG.TAG)
            .fetchOne();
        } else {
          dsl.delete(RECEIPT_TAG).where(RECEIPT_TAG.TAG.eq(tag).and(
               RECEIPT_TAG.RECEIPT_ID.equal(receipt_id))).execute();
        }


    }

    public List<ReceiptsRecord> getAllTaggedTuples(String tag) {
        return dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(dsl.selectFrom(RECEIPT_TAG).where(RECEIPT_TAG.TAG.equal(tag)).fetch(RECEIPT_TAG.RECEIPT_ID))).fetch();
    }
}
