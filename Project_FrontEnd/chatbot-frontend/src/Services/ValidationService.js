import httpCommons from "../http-commons";

export const updateValidation = (id, yes, no, count) => {
    return httpCommons.post(`/nodes/validate/${id}`, {
        id: id,
        yes: yes,
        no: no,
        count: count
    })
}

export const get = (nodeId, question, answer, childId, parentId) => {
    return httpCommons.post(`/nodes/${nodeId}`), {
        id: nodeId,
        question: question,
        answer: answer,
        childId: childId,
        parentId: parentId
    }
}