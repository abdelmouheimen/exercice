import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './Home';
import PoliceList from './PoliceList';
import PoliceEdit from './PoliceEdit';

function App() {
  return (
    <Router>
      <Switch>
      <Route path='/' exact={true} component={Home}/>
      <Route path='/polices' exact={true} component={PoliceList}/>
      <Route path='/polices/:id' component={PoliceEdit}/>
        {/* <Route path='/' exact={true} component={Home}/>
        <Route path='/clients' exact={true} component={ClientList}/>
        <Route path='/clients/:id' component={ClientEdit}/> */}
      </Switch>
    </Router>
)
}

export default App;
