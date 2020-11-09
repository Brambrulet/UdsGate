package ru.brambrulet.request;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Item;
import ru.brambrulet.json.enums.ItemType;
import ru.brambrulet.json.sub.ItemVariant;
import ru.brambrulet.request.sub.UdsRequest;

@AllArgsConstructor
public class ItemRequest extends UdsRequest<Item> {

    private String json;

    private Long id;

    @Override
    protected String getUrl() {
        return Objects.isNull(id)
                ? urlBuilder("/goods").build()
                : urlBuilder("/goods/" + id).build();
    }

    @Override
    protected RequestBuilder requestBuilder() {
        return (Objects.isNull(id) ? RequestBuilder.post() : RequestBuilder.put())
                .setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    }

    public static CategoryBuilder newCategory() {
        return new Builder(ItemType.CATEGORY);
    }

    public static CategoryBuilder newCategory(UdsGate gate, Item item) {
        return new Builder(ItemType.CATEGORY, item);
    }

    public static ItemBuilder newItem() {
        return new Builder(ItemType.ITEM);
    }

    public static ItemBuilder newItem(Item item) {
        return new Builder(ItemType.ITEM, item);
    }

    public static VaryingBuilder newVarying() {
        return new Builder(ItemType.VARYING_ITEM);
    }

    public static VaryingBuilder newVarying(Item item) {
        return new Builder(ItemType.VARYING_ITEM, item);
    }

    public static CategoryBuilder updateCategory(long id) {
        return new Builder(ItemType.CATEGORY, id);
    }

    public static CategoryBuilder updateCategory(Item item) {
        return new Builder(ItemType.CATEGORY, item);
    }

    public static ItemBuilder updateItem(long id) {
        return new Builder(ItemType.ITEM, id);
    }

    public static ItemBuilder updateItem(Item item) {
        return new Builder(ItemType.ITEM, item);
    }

    public static VaryingBuilder updateVarying(long id) {
        return new Builder(ItemType.VARYING_ITEM, id);
    }

    public static VaryingBuilder updateVarying(Item item) {
        return new Builder(ItemType.VARYING_ITEM, item);
    }

    private static Item request(UdsGate gate, String json, Long id) {
        return new ItemRequest(json, id).request(gate, Item.class);
    }

    public static class Builder implements CategoryBuilder, ItemBuilder, VaryingBuilder {
        private final ItemType type;
        private Long id;
        private final ru.brambrulet.request.json.Item item = new ru.brambrulet.request.json.Item();

        public Builder(ItemType type) {
            this.type = type;
        }

        public Builder(ItemType type, Long id) {
            this.type = type;
            this.id = id;
        }

        public Builder(ItemType type, Item item) {
            this.type = type;
            this.id = item.getId();
            this.item.assign(item);
        }

        @Override
        public Builder name(String name) {
            item.name = name;
            return this;
        }

        @Override
        public Builder nodeId(Long nodeId) {
            item.nodeId = nodeId;
            return this;
        }

        @Override
        public Builder externalId(String externalId) {
            item.externalId = externalId;
            return this;
        }

        @Override
        public Builder hidden(Boolean hidden) {
            item.hidden = hidden;
            return this;
        }

        @Override
        public Builder variant(String name, String sku, Double price) {
            variants().add(new ItemVariant(name, sku, price));
            return this;
        }

        @Override
        public Builder clearVariants() {
            item.data.variants = null;
            return this;
        }

        @Override
        public Builder sku(String sku) {
            item.data.sku = sku;
            return this;
        }

        @Override
        public Builder price(Double price) {
            item.data.price = price;
            return this;
        }

        @Override
        public Builder description(String description) {
            item.data.description = description;
            return this;
        }

        @Override
        public Item request(UdsGate gate) {
            item.data.type = type;

            return ItemRequest.request(gate, new Gson().toJson(item), id);
        }

        private List<ItemVariant> variants() {
            if (item.data.variants == null) {
                item.data.variants = new ArrayList<>();
            }

            return item.data.variants;
        }
    }

    public interface CategoryBuilder {
        CategoryBuilder name(String name);
        CategoryBuilder nodeId(Long nodeId);
        CategoryBuilder externalId(String externalId);
        CategoryBuilder hidden(Boolean hidden);
        Item request(UdsGate gate);
    }

    public interface ItemBuilder {
        ItemBuilder name(String name);
        ItemBuilder nodeId(Long nodeId);
        ItemBuilder externalId(String externalId);
        ItemBuilder hidden(Boolean hidden);
        ItemBuilder sku(String sku);
        ItemBuilder price(Double price);
        ItemBuilder description(String description);
        Item request(UdsGate gate);
    }

    public interface VaryingBuilder {
        VaryingBuilder name(String name);
        VaryingBuilder nodeId(Long nodeId);
        VaryingBuilder externalId(String externalId);
        VaryingBuilder hidden(Boolean hidden);
        VaryingBuilder variant(String name, String sku, Double price);
        VaryingBuilder clearVariants();
        VaryingBuilder description(String description);
        Item request(UdsGate gate);
    }
}
