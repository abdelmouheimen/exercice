import React, { useEffect, useState } from "react";
import { Link, useHistory, withRouter } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "./AppNavbar";
import { useParams } from "react-router-dom";

const PoliceEdit = () => {
  const { id } = useParams();
  const history = useHistory();
  useEffect(() => {
    if (id !== "new") {
      const police = fetch(`/police-assurance/${id}`)
        .then((response) => response.json())
        .then((data) => setPolice(data));
    }
  }, []);
  const [police, setPolice] = useState({});

  function createPolice(p) {
    if(p["dateDebutCouverture"] === undefined)
      p["dateDebutCouverture"] = "2023-01-11T19:36:06.812Z";
    if(p["dateFinCouverture"] === undefined)
      p["dateFinCouverture"] = "2023-01-11T19:36:06.812Z";
    return JSON.stringify(p);
  }

  function handleSubmit(event) {
    event.preventDefault();
    fetch("/police-assurance", {
      method: police.id ? "PUT" : "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: createPolice(police),
    });
    history.push("/polices");
  }

  function handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = police;
    item[name] = value;
    setPolice(item);
  }

  return (
    <div>
      <AppNavbar />
      <Container>
        {<h2>{police.id ? "Edit Client" : "Add Client"}</h2>}
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="nom">nom</Label>
            <Input
              type="text"
              name="nom"
              id="nom"
              defaultValue={police.nom || ""}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <Label for="statut">statut</Label>
            <Input
              type="text"
              name="statut"
              id="statut"
              defaultValue={police.statut || ""}
              onChange={handleChange}
            />
          </FormGroup>

          <FormGroup>
            <Label for="dateDebutCouverture">dateDebutCouverture</Label>
            <Input
              type="text"
              name="dateDebutCouverture"
              id="dateDebutCouverture"
              defaultValue={
                police.dateDebutCouverture || "2023-01-11T19:36:06.812Z"
              }
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <Label for="dateFinCouverture">dateFinCouverture</Label>
            <Input
              type="text"
              name="dateFinCouverture"
              id="dateFinCouverture"
              defaultValue={
                police.dateFinCouverture || "2023-01-11T19:36:06.812Z"
              }
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">
              Save
            </Button>{" "}
            <Button color="secondary" tag={Link} to="/polices">
              Cancel
            </Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  );
};

export default PoliceEdit;
