import "./adminPanel.css";

const NodeValues = (props) => {

    return (
        <div>
            <div className="nodeValueText" >{props.answer}</div>
            <div className="nodeValueText" >{props.antwoord}</div>
        </div>
    )
}

export default NodeValues;