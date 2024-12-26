package api.bookstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBooksRequest {

    private String userId;
    private List<Isbn> collectionOfIsbns;
}