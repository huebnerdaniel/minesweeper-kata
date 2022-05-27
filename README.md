# minesweeper-kata

Here is a simple flow chart:

```mermaid
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->D;
```

```plantuml
queue procat_v3_product_aggregated
queue internal_product_age

[seo-product-age]

database "seoproductage" #lightgreen

procat_v3_product_aggregated --> [seo-product-age]: consumes product aggregates
[seo-product-age] --> internal_product_age: publishes changed product ages
[seo-product-age] --> [seo-product-age]: update product ages every night
[seo-product-age] -left-> seoproductage: persists creation date\n per product
```