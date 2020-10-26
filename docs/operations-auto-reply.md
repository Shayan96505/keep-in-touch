# Entity Operations Checklist

## Entity name: AutoReply

## Operations

In the list of operations below, check all the operations that apply. For example, if you know you will need to be able to insert a single instance of the entity at a time into the database, check **Single instance** in the **Create/insert** section.

Note that the pairs of square brackets below are rendered as checkboxes in GitHub Pages. To insert a checkmark, **replace** the single space between the square brackets in the Markdown with an "x" character (uppercase or lowercase; **do not** include the quote characters). To remove a checkmark, **replace** the "x" between the square brackets with a **single** space character. Aside from adding or removing checkmarks, do not modify the formatting or content of the remainder of this section.

### Create/insert
    
* [ ] Single instance 
* [x] Multiple instances 
    
### Read/query/select
* [ ] Single instance 
* [x] Multiple instances 

### Update

* [x] Single instance 
* [ ] Multiple instances 

### Delete

* [x] Single instance 
* [ ] Multiple instances 


## Queries

For any queries (i.e. selecting from the database) that you think you will need to do with this entity, summarize the purpose of the query (what functionality in your application will use the query), whether the query is intended to return a single instance or multiple instances (and whether returning no instances is a valid possibility), what field/column of your entity will be used as filter criteria, and in what order the results (if multiple) should be returned.

Copy and paste the section below as many times as necessary, for all of the queries you currently anticipate implementing for this entity.

### Query: {Replace this placeholder with a simple label for the query---e.g. "All favorites".}

Purpose

: {Replace this placeholder---but not the colon character and the space that precede the placeholder---with a sentence or two summarizing the purpose of the query.}

Cardinality/modality

: {Replace this placeholder---but not the colon character and the space that precede the placeholder---with the expected query cardinality & modality. Cardinality should be either "one" or "many", and modality should be either "required" or "optional". For example, a query intended to return all scores of a game has a cardinality/modality combination of "many/optional".}
 
Filter

: {Replace this placeholder---but not the colon character and the space that precede the placeholder---with the list of fields of the entity that will be used to filter the query. For example, if this is a `Comment` entity, with a `posted` field that holds the date a comment was recorded, and you intend to include a query that shows only the last 30 days of comments, you would filter on that `posted` field.}
 
Sort order

: {Replace this placeholder---but not the colon character and the space that precede the placeholder---with the list of fields of the entity that will be used to sort the query, along with an indicator of ascending or descending order. For example, if the entity is `Task` in a to-do list application, and `Task` has a `dueDate` field, you might want to say "dueDate, ascending".}

