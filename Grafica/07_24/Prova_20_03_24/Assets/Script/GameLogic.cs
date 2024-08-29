using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameLogic : MonoBehaviour {
    public GameObject coins;
    public TextMeshProUGUI score;
    public TextMeshProUGUI winText;
    public TextMeshProUGUI loseText;
    public Button restartButton;
    public GameObject finishLine;
    private GameObject[] coinList; 
    private bool isWin = false;
    void Start() {
        int childCount = coins.transform.childCount;
        coinList = new GameObject[childCount];
        for (int i = 0; i < childCount; i++) {
            coinList[i] = coins.transform.GetChild(i).gameObject;
        }
        
        winText.enabled = false;
        loseText.enabled = false;
    }

    void Update() {
        if (gameObject.transform.position.y <= -5 && !isWin) {
            loseText.enabled = true;
            Time.timeScale = 0;
        }
    }

    void OnTriggerEnter(Collider other) {
        int index = System.Array.IndexOf(coinList, other.gameObject);
        bool isPresent = index >= 0;
        if (isPresent) {
            Destroy(other.gameObject);
            if (score == null)
                Debug.Log("Score is null");
            else
                score.text = (int.Parse(score.text) + 1).ToString();
        }

        if (other.gameObject == finishLine) {
            winText.enabled = true;
            isWin = true;
        }
    }

    public void RestartGame() {
        Time.timeScale = 1;
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex); 
    }
}
