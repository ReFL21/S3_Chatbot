import httpCommons from "../http-commons";

export const createNewTopic = (question, answer, childId, parentId) => {
    return httpCommons.post("/nodes", {
        question: question,
        answer: answer,
        childId: childId,
        parentId: parentId
    })
}

// export const updateTopic = (nodeId, question, answer, childId, parentId) => {
//     return httpCommons.post(`/nodes/${nodeId}`), {
//         id: nodeId,
//         question: question,
//         answer: answer,
//         childId: childId,
//         parentId: parentId
//     }
// }
export const updateTopic = (nodeId, nodeText, answer, vraag, antwoord, parentId) => {
    return httpCommons.put(`/nodes/${nodeId}`, {
        id: nodeId,
        nodeText: nodeText,
        answer: answer,
        vraag: vraag,
        antwoord: antwoord,
        parentId: parentId
    })
}
export const deleteTopic = (nodeId) => {
    return httpCommons.delete(`/nodes/${nodeId}`);
}

export const getRatingById = (id) => {
    return httpCommons.get(`/ratings/find?id=${id}`);
}