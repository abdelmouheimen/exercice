import React, { useEffect, useState } from "react";
import { Button, ButtonGroup, Container, Table } from "reactstrap";
import { Link } from "react-router-dom";
import AppNavbar from "./AppNavbar";

const PoliceList = () => {
const [polices, setPolices] = useState([]);

  useEffect(() => {
    fetch("/police-assurance/all")
      .then((response) => response.json())
      .then((data) => setPolices(data));
  }, []);


   function remove(id) {
     fetch(`/police-assurance/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(() => {
        let updatedPolices = polices.filter(i => i.id !== id);
        setPolices(updatedPolices);
    });
}

  return (
    <div>
      <div>
        <AppNavbar />
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/polices/new">
              Add Client
            </Button>
          </div>
          <h3>Clients</h3>
          <Table className="mt-4">
            <thead>
              <tr>
              <th width="10%">id</th>
                <th width="20%">nom</th>
                <th width="20%">statut</th>
                <th width="25%">dateDebutCouverture</th>
                <th width="25%">dateFinCouverture</th>

              </tr>
            </thead>
            <tbody>
              {polices.map((police) => {
                return (
                  <tr key={police.id}>
                     <td>{police.id}</td>
                    <td style={{ whiteSpace: "nowrap" }}>{police.nom}</td>
                    <td>{police.statut}</td>
                    <td>{police.dateDebutCouverture}</td>
                    <td>{police.dateFinCouverture}</td>
                    <td>
                      <ButtonGroup>
                        <Button
                          size="sm"
                          color="primary"
                          tag={Link}
                          to={"/polices/" + police.id}
                        >
                          Edit
                        </Button>
                        <Button
                          size="sm"
                          color="danger"
                          onClick={() => remove(police.id)}
                        >
                          Delete
                        </Button>
                      </ButtonGroup>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </Container>
      </div>
    </div>
  );
};

export default PoliceList;
