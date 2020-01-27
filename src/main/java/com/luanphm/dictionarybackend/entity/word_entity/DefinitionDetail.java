package com.luanphm.dictionarybackend.entity.word_entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luanphm.dictionarybackend.constant.ElasticFields;
import lombok.*;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefinitionDetail {
    @Field(ElasticFields.DEFINITION)
    private String definition;

    @Field(ElasticFields.PART_OF_SPEECH)
    private String partOfSpeech;

    @Field(ElasticFields.EXAMPLES)
    private List<String> examples;

    @Field(ElasticFields.VERB_GROUP)
    private List<String> verbGroup;

    @Field(ElasticFields.DERIVATIONS)
    @JsonAlias("derivation")
    private List<String> derivations;

    @Field(ElasticFields.SYNONYMS)
    private List<String> synonyms;

    @Field(ElasticFields.ANTONYMS)
    private List<String> antonyms;

    @Field(ElasticFields.TYPE_OF)
    private List<String> typeOf;

    @Field(ElasticFields.HAS_TYPES)
    private List<String> hasTypes;

    @Field(ElasticFields.PART_OF)
    private List<String> partOf;

    @Field(ElasticFields.HAS_PART)
    private List<String> hasParts;

    @Field(ElasticFields.INSTANCE_OF)
    private List<String> instanceOf;

    @Field(ElasticFields.HAS_INSTANCES)
    private List<String> hasInstances;

    @Field(ElasticFields.SIMILAR_TO)
    private List<String> similarTo;

    @Field(ElasticFields.ALSO)
    private List<String> also;

    @Field(ElasticFields.ENTAILS)
    private List<String> entails;

    @Field(ElasticFields.MEMBER_OF)
    private List<String> memberOf;

    @Field(ElasticFields.HAS_MEMBERS)
    private List<String> hasMembers;

    @Field(ElasticFields.SUBSTANCE_OF)
    private List<String> substanceOf;

    @Field(ElasticFields.HAS_SUBSTANCES)
    private List<String> hasSubstances;

    @Field(ElasticFields.IN_CATEGORY)
    private List<String> inCategory;

    @Field(ElasticFields.HAS_CATEGORIES)
    private List<String> hasCategories;

    @Field(ElasticFields.USAGE_OF)
    private List<String> usageOf;

    @Field(ElasticFields.IN_REGION)
    private List<String> inRegion;

    @Field(ElasticFields.REGION_OF)
    private List<String> regionOf;

    @Field(ElasticFields.PERTAINS_TO)
    private List<String> pertainsTo;

    @Field(ElasticFields.ATTRIBUTES)
    @JsonAlias("attribute")
    private List<String> attributes;

}
