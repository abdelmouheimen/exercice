package infrastructure.utils.fixture;

import java.time.LocalDateTime;

import org.exemple.data.ETypeActivite;
import org.exemple.data.PoliceAssuranceDto;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoliceAssuranceTTemplaceLoader implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(PoliceAssuranceDto.class).addTemplate("create", new Rule(){{
            add("nom", "Abdel");
            add("statut", ETypeActivite.ACTIVITE);
            add("dateDebutCouverture", LocalDateTime.now());
            add("dateFinCouverture", LocalDateTime.now());
        }});

        Fixture.of(PoliceAssuranceDto.class).addTemplate("update", new Rule(){{
        	add("id", 1L);
        	add("nom", "Abdel2");
        	add("statut", ETypeActivite.ACTIVITE);
            add("dateDebutCouverture", LocalDateTime.now());
            add("dateFinCouverture", LocalDateTime.now());
        }});

        Fixture.of(PoliceAssuranceDto.class).addTemplate("invalid", new Rule(){{
        	add("nom", "Abdel");
            add("statut", ETypeActivite.ACTIVITE);
            add("dateDebutCouverture", LocalDateTime.now());
            add("dateFinCouverture", LocalDateTime.now());
        }});
    }
}
