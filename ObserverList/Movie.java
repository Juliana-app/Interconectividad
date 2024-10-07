package ObserverList;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class Movie {

    private String title;
    @EqualsAndHashCode.Exclude
    private String summary;
    @EqualsAndHashCode.Exclude
    private int duration;
}