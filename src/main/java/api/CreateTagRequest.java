package api;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This is an API Object.  It's job is to model and document the JSON API that we expose
 *
 * Fields can be annotated with Validation annotations - these will be applied by the
 * Server when transforming JSON requests into Java objects IFF you specify @Valid in the
 * endpoint.  See {@link controllers.TagController#createTag(CreateTagRequest)} for
 * and example.
 */
public class CreateTagRequest {
    @NotEmpty
    public Integer receipt_id;
}
