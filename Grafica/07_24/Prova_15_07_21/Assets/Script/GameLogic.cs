using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEditor;
using UnityEngine;

public class GameLogic : MonoBehaviour {
    public GameObject target;
    public GameObject floor;
    public TextMeshProUGUI score;
    public Canvas canvas;

    void OnCollisionEnter(Collision collision) {
        if (collision.gameObject == target) {
            GameObject textGameObject = new GameObject("UIText");
            TextMeshProUGUI textMeshPro = textGameObject.AddComponent<TextMeshProUGUI>();

            textMeshPro.text = "HIT!";
            textMeshPro.fontSize = 36;
            textMeshPro.color = Color.white;

            textGameObject.transform.SetParent(canvas.transform, false);

            RectTransform rectTransform = textGameObject.GetComponent<RectTransform>();
            rectTransform.anchoredPosition = new Vector3(-366f, -165.1f, 0f);
        }
        if (collision.gameObject == floor) {
            score.text = (int.Parse(score.text) + 1).ToString();
        }
    }
}
