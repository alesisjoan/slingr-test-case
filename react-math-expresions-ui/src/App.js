import React from "react";
import logo from "./logo.svg";
import "./App.css";
import getCalculateAPI from "./MathServices";

const LAST_EXPRESSIONS = "http://localhost:8090/app/last";

const App = () => {
  const [mathExpression, setMathExpression] = React.useState("");
  const [mathResult, setMathResult] = React.useState("");
  const [message, setMessage] = React.useState("");
  const [toggleAPI, setToggleAPI] = React.useState("SpringBoot");
  const [toggleMethod, setToggleMethod] = React.useState("GET");
  const [digits, setDigits] = React.useState(0);
  const [lastExpressions, setLastExpressions] = React.useState("");

  const calculate = () => {
    let callback = getCalculateAPI(toggleAPI, toggleMethod);
    callback(mathExpression, digits, setMathResult, setMessage);
    //console.log(result);
    //setMathResult(result);
    //setMathResult(callback(mathExpression));
  };

  const reset = () => {
    setMathExpression("");
    setMathResult("");
  };

  const backspace = () => {
    setMathExpression(mathExpression.slice(0, -1));
  };

  const appendMathExpression = (button) => {
    if (button === "=") {
      calculate();
    } else if (button === "C") {
      reset();
    } else if (button === "back") {
      backspace();
    } else {
      setMathExpression(mathExpression + button);
    }
  };

  const handleToggleAPI = (event) => {
    setToggleAPI(event.target.value);
  };

  const handleToggleMethod = (event) => {
    setToggleMethod(event.target.value);
  };

  const handleDigits = (event) => {
    setDigits(event.target.value);
  };

  return (
    <div className="App">
      <div className="row">
        <div className="column1">
          <div className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <p>Math Expressions demo</p>
            <a
              className="App-link"
              href="https://alesisjoan.github.io"
              target="_blank"
              rel="noopener noreferrer"
            >
              by Alesis Manzano
            </a>
            <br />
            <div>
              <h5 style={{ margin: 5 }}>Last expressions</h5>
              <LastExpressionsComponent
                lastExpressions={lastExpressions}
                setLastExpressions={setLastExpressions}
              />
            </div>
          </div>
        </div>
        <div className="column2">
          <SwitchApiComponent
            toggleAPI={toggleAPI}
            setToggleAPI={handleToggleAPI}
            toggleMethod={toggleMethod}
            setToggleMethod={handleToggleMethod}
          />
          <br />
          <DigitsComponent digits={digits} onchange={handleDigits} />
        </div>
        <div className="column3">
          <div className="calculator-body">
            <h1>Simple Calculator</h1>
            <ResultComponent mathResult={mathResult} />
            <ExpressionComponent mathExpression={mathExpression} />
            <br />
            <NumPadComponent onClickNumPad={appendMathExpression} />
            <LogComponent message={message} />
          </div>
        </div>
      </div>
    </div>
  );
};

const LastExpressionsComponent = ({ lastExpressions, setLastExpressions }) => {
  fetch(LAST_EXPRESSIONS)
    .then((response) => response.text())
    .then((responseJson) => {
      setLastExpressions(responseJson);
    });
  return (
    <pre
      style={{
        textOverflow: "ellipsis",
        fontSize: 10,
        //minWidth: '20%',
        maxWidth: 500,
        //minHeight: '10%',
        maxHeight: 150,
      }}
    >
      {lastExpressions}
    </pre>
  );
};

const DigitsComponent = ({ digits, onchange }) => {
  return (
    <div>
      <input
        name="digits"
        type="number"
        min="0"
        max="100"
        value={digits}
        onChange={onchange}
      />
      <label htmlFor="digits">Digits</label>
    </div>
  );
};

const ExpressionComponent = ({ mathExpression }) => {
  return (
    <div className="expression">
      <p className="legend">Expression</p>
      <p> {mathExpression}</p>
    </div>
  );
};

const ResultComponent = ({ mathResult }) => {
  return (
    <div className="result">
      <p className="legend">Result</p>
      <p>{mathResult}</p>
    </div>
  );
};

const LogComponent = ({ message }) => {
  return (
    <div className="log">
      <p>{message}</p>
    </div>
  );
};

const SwitchApiComponent = ({
  toggleAPI,
  setToggleAPI,
  toggleMethod,
  setToggleMethod,
}) => {
  return (
    <div>
      <form className="switch-field">
        <div className="switch-title">API</div>
        <input
          type="radio"
          id="switch_api_left"
          name="switchToggle"
          value="SpringBoot"
          onChange={setToggleAPI}
          checked={toggleAPI === "SpringBoot"}
        />
        <label htmlFor="switch_api_left">Spring Boot</label>

        <input
          type="radio"
          id="switch_api_right"
          name="switchToggle"
          value="Python"
          onChange={setToggleAPI}
          checked={toggleAPI === "Python"}
        />
        <label htmlFor="switch_api_right">Python</label>
      </form>
      <br />
      <form className="switch-field">
        <div className="switch-title">Method</div>
        <input
          type="radio"
          id="switch_method_left"
          name="switchToggle"
          value="GET"
          onChange={setToggleMethod}
          checked={toggleMethod === "GET"}
        />
        <label htmlFor="switch_method_left">GET</label>

        <input
          type="radio"
          id="switch_method_right"
          name="switchToggle"
          value="POST"
          onChange={setToggleMethod}
          checked={toggleMethod === "POST"}
        />
        <label htmlFor="switch_method_right">POST</label>
      </form>
    </div>
  );
};

const NumPadComponent = ({ onClickNumPad }) => {
  return (
    <div className="button">
      <button
        className="buttonHalf"
        name="("
        onClick={(e) => onClickNumPad(e.target.name)}
      >
        (
      </button>
      <button
        className="buttonHalf"
        name=")"
        onClick={(e) => onClickNumPad(e.target.name)}
      >
        )
      </button>
      <button name="sqrt(" onClick={(e) => onClickNumPad(e.target.name)}>
        sqrt
      </button>
      <button name="back" onClick={(e) => onClickNumPad(e.target.name)}>
        &#8592;
      </button>

      <button name="C" onClick={(e) => onClickNumPad(e.target.name)}>
        C
      </button>
      <br />

      <button name="1" onClick={(e) => onClickNumPad(e.target.name)}>
        1
      </button>
      <button name="2" onClick={(e) => onClickNumPad(e.target.name)}>
        2
      </button>
      <button name="3" onClick={(e) => onClickNumPad(e.target.name)}>
        3
      </button>
      <button name="+" onClick={(e) => onClickNumPad(e.target.name)}>
        +
      </button>
      <br />

      <button name="4" onClick={(e) => onClickNumPad(e.target.name)}>
        4
      </button>
      <button name="5" onClick={(e) => onClickNumPad(e.target.name)}>
        5
      </button>
      <button name="6" onClick={(e) => onClickNumPad(e.target.name)}>
        6
      </button>
      <button name="-" onClick={(e) => onClickNumPad(e.target.name)}>
        -
      </button>
      <br />

      <button name="7" onClick={(e) => onClickNumPad(e.target.name)}>
        7
      </button>
      <button name="8" onClick={(e) => onClickNumPad(e.target.name)}>
        8
      </button>
      <button name="9" onClick={(e) => onClickNumPad(e.target.name)}>
        9
      </button>
      <button name="*" onClick={(e) => onClickNumPad(e.target.name)}>
        x
      </button>

      <br />

      <button name="." onClick={(e) => onClickNumPad(e.target.name)}>
        .
      </button>
      <button name="0" onClick={(e) => onClickNumPad(e.target.name)}>
        0
      </button>
      <button name="=" onClick={(e) => onClickNumPad(e.target.name)}>
        =
      </button>
      <button name="/" onClick={(e) => onClickNumPad(e.target.name)}>
        ÷
      </button>
      <br />
    </div>
  );
};

export default App;
